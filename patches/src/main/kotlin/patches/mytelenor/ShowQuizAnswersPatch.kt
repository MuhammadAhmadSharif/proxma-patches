package patches.mytelenor

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.PatchException
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patcher.patch.resourcePatch
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.iface.ClassDef
import com.android.tools.smali.dexlib2.iface.Field
import com.android.tools.smali.dexlib2.iface.value.StringEncodedValue
import org.w3c.dom.Element

/*
 * Show the Answer & Win / Test Your Skills answer key, for logged-in users AND guests.
 *
 * The questions response already carries `isCorrect` on every choice (the app uses it to colour the
 * right option after a tap). Instead of touching the network stack (OkHttp/Retrofit are R8-obfuscated,
 * so a runtime hook by class name finds nothing), this patch hooks the app's own Gson models, which
 * keep their JSON keys via @SerializedName:
 *
 *   - the response data class   (has a "triviaQuestions" field)  -> QuizHelper.onQuestions(this)
 *   - each question class       (has a "choices" field)          -> QuizHelper.onQuestion(this)
 *   - each choice class         (has an "isCorrect" field)       -> QuizHelper.onChoice(this)
 *
 * The call is injected at the top of the model's no-arg getters (List getters for data/question,
 * String getters for choice), so it runs right when the quiz screen reads the data to render it. The
 * extension then appends " ✓" to the text of every correct choice and posts a notification listing
 * all correct answers. Choice IDs (what the app submits) are never changed.
 */

private const val QUIZ_HELPER = "Lapp/proxma/mytelenor/QuizHelper;"
private const val APP_PREFIX = "Lcom/telenor/pakistan/mytelenor/"
private const val SERIALIZED_NAME = "Lcom/google/gson/annotations/SerializedName;"

private val LIST_TYPES = setOf("Ljava/util/List;", "Ljava/util/ArrayList;", "Ljava/util/Collection;")

/** The JSON key Gson maps this field to: @SerializedName("...") if present, else the field name. */
private fun Field.jsonKey(): String {
    val annotation = annotations.firstOrNull { it.type == SERIALIZED_NAME }
    val value = annotation?.elements?.firstOrNull { it.name == "value" }?.value as? StringEncodedValue
    return value?.value ?: name
}

private fun ClassDef.hasJsonKey(key: String) = fields.any { it.jsonKey().equals(key, ignoreCase = true) }

private val quizNotificationPermissionPatch = resourcePatch {
    finalize {
        document("AndroidManifest.xml").use { doc ->
            val manifest = doc.documentElement
            val permissions = doc.getElementsByTagName("uses-permission")
            val permission = "android.permission.POST_NOTIFICATIONS"
            if ((0 until permissions.length).none {
                    (permissions.item(it) as Element).getAttribute("android:name") == permission
                }
            ) {
                manifest.insertBefore(
                    doc.createElement("uses-permission").apply { setAttribute("android:name", permission) },
                    manifest.firstChild,
                )
            }
        }
    }
}

@Suppress("unused")
val showQuizAnswersPatch = bytecodePatch(
    name = "Show quiz answers",
    description = "Marks the correct option with a tick (✓) in Answer & Win / Test Your Skills and shows all " +
        "correct answers in a notification as soon as the quiz opens. Works for logged-in users and in guest mode. " +
        "Nothing is answered or submitted for you.",
) {
    compatibleWith(COMPATIBILITY_MYTELENOR)
    extendWith("extensions/extension.mpe")
    dependsOn(quizNotificationPermissionPatch)

    execute {
        val dataClasses = mutableListOf<ClassDef>()
        val questionClasses = mutableListOf<ClassDef>()
        val choiceClasses = mutableListOf<ClassDef>()

        // Collect first: mutating classes while the patcher walks the class list is not safe.
        classDefForEach { classDef ->
            if (!classDef.type.startsWith(APP_PREFIX)) return@classDefForEach
            if (AccessFlags.INTERFACE.isSet(classDef.accessFlags)) return@classDefForEach
            when {
                classDef.hasJsonKey("isCorrect") -> choiceClasses += classDef
                classDef.hasJsonKey("choices") -> questionClasses += classDef
                classDef.hasJsonKey("triviaQuestions") -> dataClasses += classDef
            }
        }

        if (choiceClasses.isEmpty()) {
            throw PatchException("Show quiz answers: no quiz choice model (field \"isCorrect\") found in this My Telenor build")
        }

        fun hookGetters(classDef: ClassDef, callback: String, acceptReturnType: (String) -> Boolean): Int {
            var hooked = 0
            mutableClassDefBy(classDef).methods
                .filter { method ->
                    method.implementation != null &&
                        !AccessFlags.STATIC.isSet(method.accessFlags) &&
                        !AccessFlags.ABSTRACT.isSet(method.accessFlags) &&
                        method.parameterTypes.isEmpty() &&
                        method.name != "<init>" &&
                        method.name != "toString" &&
                        acceptReturnType(method.returnType)
                }
                .forEach { method ->
                    method.addInstructions(
                        0,
                        "invoke-static/range {p0 .. p0}, $QUIZ_HELPER->$callback(Ljava/lang/Object;)V",
                    )
                    hooked++
                }
            return hooked
        }

        var hooked = 0
        dataClasses.forEach { hooked += hookGetters(it, "onQuestions") { type -> type in LIST_TYPES } }
        questionClasses.forEach { hooked += hookGetters(it, "onQuestion") { type -> type in LIST_TYPES } }
        choiceClasses.forEach { hooked += hookGetters(it, "onChoice") { type -> type == "Ljava/lang/String;" } }

        if (hooked == 0) {
            throw PatchException("Show quiz answers: found the quiz models but none of their getters could be hooked")
        }

        // Startup hook: confirms the patch is installed (one toast) and asks for notification permission
        // on Android 13+, so the answers notification can actually be shown.
        mytelenorMainActivityOnCreateFingerprint.method.addInstructions(
            0,
            "invoke-static/range {p0 .. p0}, $QUIZ_HELPER->onAppStart(Landroid/app/Activity;)V",
        )
    }
}

