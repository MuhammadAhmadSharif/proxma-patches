## 1.0.0 (2026-11-01)

### Bug Fixes

* auto-claim daily reward ladder from background worker ([f0f8545](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/f0f8545b74c391897b7a325cc444428d9e364874))
* compile errors in unlock-daily-reward patch ([e6c426b](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/e6c426b1a8bcc10ba35f59c5ea287bfdffaca9b4))
* delist Daraz — remove patches (unusable on re-signed builds) ([10256c9](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/10256c9f318e0e5967818b11e297bde3c2eba9f9))
* foodpanda — add second anti-tamper layer short-circuiting the block-screen orchestrator ([c2daf5c](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/c2daf5c4d54a5441c3a9b1dea242ebb629138d30))
* foodpanda — add second anti-tamper layer short-circuiting the block-screen orchestrator ([2b1a90c](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/2b1a90c17c8368cff45a208642cbbcbb545b9a59))
* foodpanda — build the toggle row without the app's classes ([ec64d41](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/ec64d41dafd7744ac876e7df37373f5f369780fd))
* foodpanda — ship the dark mode toggle's classes in the bundle ([34b53cf](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/34b53cf6a7b1dd781a4e8b64eac8927ff31bc982))
* foodpanda dark mode — empty FIELD_OVERRIDES so remapped fields actually darken ([9f575b6](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/9f575b6f6c813ea32cff04a4934a50fcacbe290d))
* mark Daraz patch as WIP ([2241654](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/224165422fb56de8b90e40f3c75fe2fbadd225c2))
* **mytelenor:** auto-play Answer & Win and claim its MBs; harden the daily claims ([05aa0d0](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/05aa0d0731e7fd4a39616b8dd682246851a4ed6b))
* **mytelenor:** make background runs work without opening the app ([3f16fe8](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/3f16fe8f8bf9937d998ce97a4ad7594dd3428b8d))
* **mytelenor:** rewrite StreakClaim to mirror real interceptor headers + signature ([cb040b0](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/cb040b00fabc2165048e86a98b08aee916a02d51))
* **mytelenor:** run the daily claims in a foreground service so ColorOS doesn't cut them off ([fb77f35](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/fb77f35fb4395e868b9be6de5f9cb00053fb7832))
* **mytelenor:** schedule auto-claim with JobScheduler; app R8-renames WorkManager ([dd0b185](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/dd0b185d8e300ab815fca07264ae117c83b6fdac))
* MyZong — fully kill AdMob (auto-init provider + all ad loads) ([c86511a](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/c86511a0f20e2cb0a06cc04f6f685c55019f84de))
* MyZong — stub AppOpenAd (launch ad slipped past v1.2.1) ([d9b75b0](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/d9b75b06db0c63228a7709af833ce79df6316e1c))
* MyZong daily reward — remove the popup too, claim directly ([ac03480](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/ac03480659e7ec0cbe453dbcd57bba103c25ee5e))
* **netmonster:** bypass Google Play PairIP license check on 5.0.x ([49ca553](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/49ca5534e543b7875442a06503cb61844a312220))
* **netmonster:** expose the Google Maps key option in the patch list ([2057c0d](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/2057c0d5fb7b6ae41ea2d92432c1ff655ef1edf5))
* **netmonster:** expose the Google Maps key option in the patch list ([2300b42](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/2300b42dd80e7eae53fda45b10884028e268f0b7))
* **netmonster:** retarget premium patch to 5.0.0 / 5.0.1 ([7226f17](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/7226f1799c29bf6aaefae60db1adb28e318fe941))
* **netmonster:** retarget premium patch to 5.0.0 / 5.0.1 ([3151d22](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/3151d223f4099b362a4871cf48cf46499f87eba7))
* **netmonster:** retarget Unlock premium for 3.4.3 ([7b61965](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/7b619658a9a15a50d581e45098e9c646adaa8d98))
* **netmonster:** show premium as owned on 5.0.x (card reads a date, not the flag) ([606032b](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/606032b4b237751afdc1011ceb29bf8982baec61)), closes [#32](https://github.com/MuhammadAhmadSharif/proxma-patches/issues/32)
* resolve cold-start no-account skip in background worker ([29fa73f](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/29fa73f161b1a0dcd63b983b20d02046b236f069))
* **simosa:** add the daily check-in worker instead of hooking a class the app lacks ([1685d68](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/1685d68edc24fa9eafc5b3a234246d52ab9ed023)), closes [#41](https://github.com/MuhammadAhmadSharif/proxma-patches/issues/41)
* **simosa:** B26 — stale token retry, 409 day-gate, JSON fallback ([50e4fbf](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/50e4fbfb28137748dea2f78986a339ceaca7ca79))
* **simosa:** B27 NO_SESSION cold-start + B28 notification channel ([768b557](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/768b557d5b269dfc127568e93ab5292649edd288))
* **simosa:** claim from t2's completion callback with correct guards and a daily latch ([8ca23d7](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/8ca23d717be085f41fa78117fcf836bbc7ea4b05))
* **simosa:** claim milestone levels through the app's signed claimCheckIn ([d425f82](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/d425f82763d60f1e429c201ae848899c7853ce66))
* **simosa:** claim the daily check-in reward as soon as the state loads ([6156b95](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/6156b9510543c0dae72eccce8c8f0ef3278fe650))
* **simosa:** claim the daily check-in reward as soon as the state loads ([3ab6a78](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/3ab6a78fe9916637f37a9181e002e6aa38727991))
* **simosa:** claim the Daily Rewards rung on every response, not on screen open ([d13da7c](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/d13da7c24c7e189f32d79cde29064009a559615e)), closes [#29](https://github.com/MuhammadAhmadSharif/proxma-patches/issues/29) [#29](https://github.com/MuhammadAhmadSharif/proxma-patches/issues/29)
* **simosa:** claim the daily rung when the rewards screen is shown ([72e7a56](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/72e7a561c2de37de7962eb4604c3a9c0e8de6a77)), closes [#29](https://github.com/MuhammadAhmadSharif/proxma-patches/issues/29)
* **simosa:** claim the daily rung when the rewards screen is shown ([5d9f367](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/5d9f36782d543193f1e0e2751bfe749d6caabce7)), closes [#29](https://github.com/MuhammadAhmadSharif/proxma-patches/issues/29)
* **simosa:** fire one-shot worker immediately on first launch ([3c9b748](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/3c9b748bd7ffdf0590dc09bb285be53f3bbf48ae))
* **simosa:** force the identifier accessor without Java 9's canAccess ([188813d](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/188813da568d635f561d9153ab818bf1ac6f903b))
* **simosa:** hide the Social+ tab as well, by its own route ([e2c1488](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/e2c14889b6bb5017f8ffc3bb56fd5923264c485b)), closes [#31](https://github.com/MuhammadAhmadSharif/proxma-patches/issues/31)
* **simosa:** let the streak restore go through when the restore ad can't show ([6ca6e12](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/6ca6e12adb95d57e6e0b1a8d29b4762584b35c8c)), closes [#45](https://github.com/MuhammadAhmadSharif/proxma-patches/issues/45) [#58](https://github.com/MuhammadAhmadSharif/proxma-patches/issues/58)
* **simosa:** match the coroutine launch by shape, not the repackaged nb/ class ([ffa1489](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/ffa1489a721eb2ab1057cfec5bae605bcd562f2d))
* **simosa:** refresh stale token on milestone 419/401 ([0a5f531](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/0a5f53130462fea45d49ca17043e0e0d9e59eb25))
* **simosa:** refuse to inject the tab filter into an unexpected register ([c47d68c](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/c47d68c594a2b5c6f41189e8d8ff0f69267d6123))
* **simosa:** resolve StateFlow descriptor dynamically to fix 3.3.5 crash ([e3729f2](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/e3729f273c62f741a9ed0b525d1488f1a9cc1f61))
* **simosa:** retry on transient failure with exponential backoff ([eb06a8b](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/eb06a8bf036572fec9fabe49d8e6e6ed4e9bbf07))
* **simosa:** retry transient refresh failures, fix notification bugs ([4e1e6e4](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/4e1e6e4f32620097c7ecb8bb0b564bd770bc5add))
* **simosa:** revert milestone claim changes ([b1e0cde](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/b1e0cde0647b1a4713491598b4c35e1846137a4b))
* **simosa:** run the auto-claim after the response is applied, with the app's own guards ([13e64cb](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/13e64cb4be8e459eb4a254475adf5686bf815cfc))
* **simosa:** schedule worker at 00:05 PKT instead of 24h drift ([c9e77ec](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/c9e77ecd8da5480bcde319cd421796952fc2d05c))
* **simosa:** suppress notification on no-op worker re-runs ([dd67966](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/dd6796616cf30cbdc1e668d2c0f06aaffc4e8925))
* **simosa:** switch the app's Compose theme to dark instead of editing XML ([a0aa4a0](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/a0aa4a0638722d1d9cd57857950fb7782111c5d0))
* **simosa:** target Simosa 3.3.4.2 ([aa5290a](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/aa5290a87920d8e27f16431104305c0e05ae1fde))
* storywise — block force-update prompt ([3e75f78](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/3e75f78e7dfd21088dd52c3a9e5aa3b30ba6df05))
* storywise — embedded credential restore (sw_cred.json) in bypass login ([1ddf992](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/1ddf992db8f7f69f543e8813bbf3bb65c223b8fd))
* storywise — substitute Google sign-in with anonymous auth (A3) ([5224d71](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/5224d71e916f759fc2080673c6ad224fd186e1ba))
* storywise — use real signInAnonymously chain in bypass login ([378d3b7](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/378d3b72289c66470844c7461502dfaa7a8f1995))

### New Features

* add foodpanda Bypass anti-tamper + Unlock premium patches ([3f9625a](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/3f9625a36dcec29879e3520117629f3a96e3edfb))
* add foodpanda order-tracking ad + PandaMart popup removal ([000b9ab](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/000b9ab1ac611c290386c4a7c078e2dfa908b70f))
* add mtproxy Remove ads patch ([56b0572](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/56b05722e514be7e0b2c3833a1fbe4c111a8e9d5))
* add mytelenor Bypass device validation patch ([0d8538b](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/0d8538b79a3348e544b947acf4d8617ef1dc0694))
* add mytelenor patches (Remove ads, Block trackers, manifest flags) ([f083d99](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/f083d992d5111fd28e095eeb22924e34a37b159c))
* add Safar (com.safar.fyi) ad removal ([a985445](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/a985445ff18cead91fca9b32ca2ab77515e3fad1))
* bypass PairIP license check for Safar re-signed builds ([9fb0f42](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/9fb0f421b96a7ed08c307bbbe8b5c2a734881765))
* Daraz (com.daraz.android) remove analytics & tracking ([58da7df](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/58da7dfc512cf38958ea5c05a9038c6ca3db8e6d))
* foodpanda — dark mode toggle in settings ([754cdcd](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/754cdcda6a3c295ef37e72de5e0e563564628d76))
* foodpanda — remove home/reels ad banners, CrossSell popup, and forced review prompt ([34f2383](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/34f2383bf6fec329d14228837a382c6b9436b5b1))
* **indrive:** add remove-ads patch for sinet.startup.inDriver 5.188.0 / 5.189.0 ([854caf5](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/854caf520e62c783a1841564878bfed70ac2d004))
* **indrive:** add remove-ads patch for sinet.startup.inDriver 5.188.0 / 5.189.0 ([83929ab](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/83929ab12a1e6ca1fccd675c0a14e847064a8ebe))
* Investify unlock premium + PairIP bypass ([177f167](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/177f1670aa1c0d0a1076548b7d7a4bb7533034e2))
* **mytelenor:** auto-claim daily streak MBs and quiz rewards ([49ddd6c](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/49ddd6caca9f537cbe9f53195c65b341de86fea4))
* **mytelenor:** bump version to 1.28.0 and register show quiz answers patch ([9f35a51](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/9f35a511f66127d3ab12971d406ee6f6d8ffb2b4))
* MyZong — stub AppsFlyer/TikTok SDK init too ([a2c4987](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/a2c4987c277a9c41f9331b7572a454132c81537f))
* MyZong — unlock daily reward without watching ad ([28a2dce](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/28a2dcecc45a251d5e3ddae070f6781a68241240))
* MyZong (com.zong.customercare) remove ads & tracking ([d644774](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/d64477411029749ce51d484578ee964fa45ca5f9))
* NetMonster unlock premium ([07f016d](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/07f016d8f17c0881c241d0d1c7c4c4ba444dad0a))
* **olx:** add Force dark mode patch ([4512252](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/451225254ade78453d584b9c62a9518d40bd222c))
* **olx:** add OLX India remove-ads and remove-Elite-promotions patches ([287e53a](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/287e53ada4706b76c2b899c7412e8ca82ea9fcfe)), closes [#24](https://github.com/MuhammadAhmadSharif/proxma-patches/issues/24)
* **olx:** add Remove ads patch ([de909d3](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/de909d381e7504b04ff1bc97bd5caf182ed43d36))
* **olx:** remove the item-detail (PDP) banner ad slots ([b868e21](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/b868e21bdae5f7e209ffce7d34e5142de2d0ce1d))
* **olx:** remove the item-detail (PDP) banner ad slots ([d04e2f7](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/d04e2f714e98d2d33dda3aef450996933fa6cafe))
* **olx:** suppress home banner and Google banner container loads ([b7a2580](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/b7a2580e9023d96bc40f891f950e0a39ac7bf7c9))
* Proxma patches for Simosa (Jazz World) ([6d3db83](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/6d3db837b0c6c15591efc881ad221ff979c56359))
* **simosa:** add background auto-claim worker with token refresh and notification ([2fdfc23](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/2fdfc23c140a8b92c1a8a09f2ce24d1f067a49cf))
* **simosa:** add BackgroundWorkerPatch — B26 check-in via Kotlin patcher ([7f376bf](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/7f376bf2dc8b36973866105bb3131d58c7ad9d6b))
* **simosa:** add pure black (AMOLED) background patch ([2318a68](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/2318a68443b68196aaba20219607dc4c64720e05)), closes [#000000](https://github.com/MuhammadAhmadSharif/proxma-patches/issues/000000)
* **simosa:** auto-claim milestone level rewards (day 10/20/30+) ([0688f3b](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/0688f3b40a8c66732cc2d6421e5859b5e78a9f15))
* **simosa:** remove daily check-in / SocialPlus feed ads ([a9f37e9](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/a9f37e97d40a9728fae95f8023c4cd83646b5118))
* **simosa:** remove day-10/20/30 check-in milestone RewardedAd ([3f698a1](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/3f698a16e9db192c6fd463eec617f3bbda9a0905))
* **simosa:** remove Shop, Social+ and Lifestyle tabs ([98ac831](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/98ac8319c2e852b0dbbd64b83e90d3c9ca872db5)), closes [#31](https://github.com/MuhammadAhmadSharif/proxma-patches/issues/31)
* **simosa:** split check-in ad removal into its own patch ([9d69857](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/9d69857fcd041e3cd986a128caa21b85b985f3e0)), closes [morphe-manager#616](https://github.com/MuhammadAhmadSharif/morphe-manager/issues/616) [#616](https://github.com/MuhammadAhmadSharif/proxma-patches/issues/616)
* **simosa:** support Simosa 3.3.5 ([5bac4e1](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/5bac4e1a6a3d7eea27eb585220f2b19bfb82ff7b))
* storywise — bypass login and unlock pass ([1193064](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/1193064b1fc6a1eb23c627d14c40034b9926f875))
* update NetMonster premium unlock for 4.0.4 ([56e2dcd](https://github.com/MuhammadAhmadSharif/proxma-patches/commit/56e2dcdc2afe0686996cb9f964df4b6d7338b0d0))

## [1.28.0](https://github.com/MuhammadAhmadSharif/proxma-patches/releases/tag/1.28.0) (2026-11-01)

### Features

* **mytelenor:** show quiz answers with tick (✓) and notification in guest and logged-in mode

## [1.27.5](https://github.com/totsiaw/proxma-patches/compare/1.27.4...1.27.5) (2026-10-31)

### Bug Fixes

* **mytelenor:** make background runs work without opening the app ([3f16fe8](https://github.com/totsiaw/proxma-patches/commit/3f16fe8f8bf9937d998ce97a4ad7594dd3428b8d))
* **mytelenor:** run the daily claims in a foreground service so ColorOS doesn't cut them off ([fb77f35](https://github.com/totsiaw/proxma-patches/commit/fb77f35fb4395e868b9be6de5f9cb00053fb7832))

## [1.27.4](https://github.com/totsiaw/proxma-patches/compare/1.27.3...1.27.4) (2026-10-28)

### Bug Fixes

* **simosa:** let the streak restore go through when the restore ad can't show ([6ca6e12](https://github.com/totsiaw/proxma-patches/commit/6ca6e12adb95d57e6e0b1a8d29b4762584b35c8c)), closes [#45](https://github.com/totsiaw/proxma-patches/issues/45) [#58](https://github.com/totsiaw/proxma-patches/issues/58)

## [1.27.3](https://github.com/totsiaw/proxma-patches/compare/1.27.2...1.27.3) (2026-10-26)

### Bug Fixes

* **mytelenor:** auto-play Answer & Win and claim its MBs; harden the daily claims ([05aa0d0](https://github.com/totsiaw/proxma-patches/commit/05aa0d0731e7fd4a39616b8dd682246851a4ed6b))

## [1.27.2](https://github.com/totsiaw/proxma-patches/compare/1.27.1...1.27.2) (2026-10-26)

### Bug Fixes

* **simosa:** claim milestone levels through the app's signed claimCheckIn ([d425f82](https://github.com/totsiaw/proxma-patches/commit/d425f82763d60f1e429c201ae848899c7853ce66))

## [1.27.1](https://github.com/totsiaw/proxma-patches/compare/1.27.0...1.27.1) (2026-10-26)

### Bug Fixes

* **mytelenor:** rewrite StreakClaim to mirror real interceptor headers + signature ([cb040b0](https://github.com/totsiaw/proxma-patches/commit/cb040b00fabc2165048e86a98b08aee916a02d51))
* **mytelenor:** schedule auto-claim with JobScheduler; app R8-renames WorkManager ([dd0b185](https://github.com/totsiaw/proxma-patches/commit/dd0b185d8e300ab815fca07264ae117c83b6fdac))
* **simosa:** revert milestone claim changes ([b1e0cde](https://github.com/totsiaw/proxma-patches/commit/b1e0cde0647b1a4713491598b4c35e1846137a4b))

## [1.27.0](https://github.com/totsiaw/proxma-patches/compare/1.26.9...1.27.0) (2026-10-23)

### New Features

* **mytelenor:** auto-claim daily streak MBs and quiz rewards ([49ddd6c](https://github.com/totsiaw/proxma-patches/commit/49ddd6caca9f537cbe9f53195c65b341de86fea4))

## [1.26.8](https://github.com/totsiaw/proxma-patches/compare/1.26.7...1.26.8) (2026-10-22)

### Bug Fixes

* resolve cold-start no-account skip in background worker ([a05eccb](https://github.com/totsiaw/proxma-patches/commit/a05eccbb8af6b04032ed17b7200d75b46ed4c242))

## [1.26.7](https://github.com/totsiaw/proxma-patches/compare/1.26.6...1.26.7) (2026-10-22)

### Bug Fixes

* auto-claim daily reward ladder from background worker ([a876bba](https://github.com/totsiaw/proxma-patches/commit/a876bba4f3ea85eb0f0b9bb1c96d979f43f688c2))

## [1.26.6](https://github.com/totsiaw/proxma-patches/compare/1.26.5...1.26.6) (2026-10-22)

### Bug Fixes

* **simosa:** refresh stale token on milestone 419/401 ([51dc4f4](https://github.com/totsiaw/proxma-patches/commit/51dc4f4dc6e6a610448d58311efd277949c81737))

## [1.26.5](https://github.com/totsiaw/proxma-patches/compare/1.26.4...1.26.5) (2026-10-22)

### Bug Fixes

* **simosa:** retry transient refresh failures, fix notification bugs ([105f94f](https://github.com/totsiaw/proxma-patches/commit/105f94fca5cce4c0459dbe430bb8149b6b17793b))

## [1.26.4](https://github.com/totsiaw/proxma-patches/compare/1.26.3...1.26.4) (2026-10-22)

### Bug Fixes

* **simosa:** retry on transient failure with exponential backoff ([0484bb3](https://github.com/totsiaw/proxma-patches/commit/0484bb3687e4bcfa77d7dc96b18d43870350628d))

## [1.26.3](https://github.com/totsiaw/proxma-patches/compare/1.26.2...1.26.3) (2026-10-22)

### Bug Fixes

* **simosa:** schedule worker at 00:05 PKT instead of 24h drift ([2141977](https://github.com/totsiaw/proxma-patches/commit/214197741e0b79dbb77ebb545b1237ef377e547e))

## [1.26.2](https://github.com/totsiaw/proxma-patches/compare/1.26.1...1.26.2) (2026-10-22)

### Bug Fixes

* **simosa:** hide the Social+ tab as well, by its own route ([d15e804](https://github.com/totsiaw/proxma-patches/commit/d15e804a78bfca6935c1769f3da1189c66e7a2f1)), closes [#31](https://github.com/totsiaw/proxma-patches/issues/31)
* **simosa:** refuse to inject the tab filter into an unexpected register ([081d90a](https://github.com/totsiaw/proxma-patches/commit/081d90adbab9119a5818245db763e0256570970d))

## [1.25.1](https://github.com/totsiaw/proxma-patches/compare/1.25.0...1.25.1) (2026-10-22)

### Bug Fixes

* **simosa:** add the daily check-in worker instead of hooking a class the app lacks ([57e91dc](https://github.com/totsiaw/proxma-patches/commit/57e91dc99a4c10328c6fc8cbf58160c04cd01f10)), closes [#41](https://github.com/totsiaw/proxma-patches/issues/41)

## [1.25.0](https://github.com/totsiaw/proxma-patches/compare/1.24.0...1.25.0) (2026-10-22)

### Bug Fixes

* **simosa:** B26 — stale token retry, 409 day-gate, JSON fallback ([6a486e1](https://github.com/totsiaw/proxma-patches/commit/6a486e1c3eed2ab55b2d5e0035659e96b385303a))
* **simosa:** B27 NO_SESSION cold-start + B28 notification channel ([7b04280](https://github.com/totsiaw/proxma-patches/commit/7b0428036891f42bd7ff7cbd22ba57bada769296))

### New Features

* **simosa:** add background auto-claim worker with token refresh and notification ([8bff6cf](https://github.com/totsiaw/proxma-patches/commit/8bff6cfdb6492aca060c7532e887bea2c2086d58))
* **simosa:** add BackgroundWorkerPatch — B26 check-in via Kotlin patcher ([d0ee564](https://github.com/totsiaw/proxma-patches/commit/d0ee56495a550a5ef645e256258575af9f432d0a))

## [1.24.0](https://github.com/totsiaw/proxma-patches/compare/1.23.2...1.24.0) (2026-10-21)

### Bug Fixes

* **simosa:** force the identifier accessor without Java 9's canAccess ([c698c88](https://github.com/totsiaw/proxma-patches/commit/c698c88b8b558dda3f97bef73a9bfbd05401a510))

### New Features

* **simosa:** remove Shop, Social+ and Lifestyle tabs ([d202b1e](https://github.com/totsiaw/proxma-patches/commit/d202b1ecb173dc196f6956bec693530e7188c8dc)), closes [#31](https://github.com/totsiaw/proxma-patches/issues/31)

## [1.23.2](https://github.com/totsiaw/proxma-patches/compare/1.23.1...1.23.2) (2026-10-21)

### Bug Fixes

* **simosa:** claim from t2's completion callback with correct guards and a daily latch ([33aebbd](https://github.com/totsiaw/proxma-patches/commit/33aebbd3295d3e95cc15b5949b1762bc2b563886))
* **simosa:** run the auto-claim after the response is applied, with the app's own guards ([0e48a21](https://github.com/totsiaw/proxma-patches/commit/0e48a21fd2d2ea7497ccb1cb2a8c1ba4482ed8dd))

## [1.23.1](https://github.com/totsiaw/proxma-patches/compare/1.23.0...1.23.1) (2026-10-21)

### Bug Fixes

* **simosa:** claim the Daily Rewards rung on every response, not on screen open ([69ea3b1](https://github.com/totsiaw/proxma-patches/commit/69ea3b1bf88882885a3a2d59679a596cccbaf2c3)), closes [#29](https://github.com/totsiaw/proxma-patches/issues/29) [#29](https://github.com/totsiaw/proxma-patches/issues/29)
* **simosa:** match the coroutine launch by shape, not the repackaged nb/ class ([af1231c](https://github.com/totsiaw/proxma-patches/commit/af1231cf46d03518c20f06345b8b9e38d1c1ad86))

## [1.23.0](https://github.com/totsiaw/proxma-patches/compare/1.22.2...1.23.0) (2026-10-21)

### New Features

* **olx:** suppress home banner and Google banner container loads ([a4f3d5f](https://github.com/totsiaw/proxma-patches/commit/a4f3d5fcada5b9d453ae30afd2d5c7de5fe34472))

## [1.22.2](https://github.com/totsiaw/proxma-patches/compare/1.22.1...1.22.2) (2026-10-21)

### Bug Fixes

* **simosa:** resolve StateFlow descriptor dynamically to fix 3.3.5 crash ([8d34d69](https://github.com/totsiaw/proxma-patches/commit/8d34d693b8936d398449a4ed8b52a38c8aab49f7))

## [1.22.1](https://github.com/totsiaw/proxma-patches/compare/1.22.0...1.22.1) (2026-10-21)

### Bug Fixes

* **simosa:** switch the app's Compose theme to dark instead of editing XML ([1eb7f8c](https://github.com/totsiaw/proxma-patches/commit/1eb7f8cf2a1d1630f66380f96110805fa52c36eb))

## [1.22.0](https://github.com/totsiaw/proxma-patches/compare/1.21.0...1.22.0) (2026-10-20)

### New Features

* **simosa:** add pure black (AMOLED) background patch ([4788869](https://github.com/totsiaw/proxma-patches/commit/47888698a54a5de0f824da52ff753b7cb393ac13)), closes [#000000](https://github.com/totsiaw/proxma-patches/issues/000000)

## [1.21.0](https://github.com/totsiaw/proxma-patches/compare/1.20.0...1.21.0) (2026-10-20)

### New Features

* **simosa:** support Simosa 3.3.5 ([f9cbd96](https://github.com/totsiaw/proxma-patches/commit/f9cbd96275ebff5e8b46098cdf35a45f499ce586))

## [1.20.0](https://github.com/totsiaw/proxma-patches/compare/1.19.1...1.20.0) (2026-10-20)

### Bug Fixes

* **netmonster:** expose the Google Maps key option in the patch list ([f88ad0e](https://github.com/totsiaw/proxma-patches/commit/f88ad0e2f959846caa9bc18f88ae4d5b60610892))
* **netmonster:** retarget premium patch to 5.0.0 / 5.0.1 ([c81d553](https://github.com/totsiaw/proxma-patches/commit/c81d553509f26bafa707fbb00dda26dc2b91a25c))
* **simosa:** claim the daily check-in reward as soon as the state loads ([03b9885](https://github.com/totsiaw/proxma-patches/commit/03b988599b51e3c582063845416b2623b3908049))
* **simosa:** claim the daily rung when the rewards screen is shown ([2183c29](https://github.com/totsiaw/proxma-patches/commit/2183c29575f24f67bfeed8906740c932ae23ece8)), closes [#29](https://github.com/totsiaw/proxma-patches/issues/29)

### New Features

* **indrive:** add remove-ads patch for sinet.startup.inDriver 5.188.0 / 5.189.0 ([3d4c6f8](https://github.com/totsiaw/proxma-patches/commit/3d4c6f885c478eae8e545a27dd0ad1b1f2a9ae41))
* **olx:** remove the item-detail (PDP) banner ad slots ([c2833a3](https://github.com/totsiaw/proxma-patches/commit/c2833a37a9d6cebbd842f6a05300998d5c1e9e8c))

## [1.19.1](https://github.com/totsiaw/proxma-patches/compare/1.19.0...1.19.1) (2026-10-20)

### Bug Fixes

* **netmonster:** show premium as owned on 5.0.x (card reads a date, not the flag) ([fb83d9c](https://github.com/totsiaw/proxma-patches/commit/fb83d9cec781108223558219d82a91bdbc99173b)), closes [#32](https://github.com/totsiaw/proxma-patches/issues/32)

## [1.19.0](https://github.com/totsiaw/proxma-patches/compare/1.18.1...1.19.0) (2026-10-20)

### Bug Fixes

* **simosa:** claim the daily rung when the rewards screen is shown ([3c17535](https://github.com/totsiaw/proxma-patches/commit/3c1753563f83e500b53062d0abd940db1830cfde)), closes [#29](https://github.com/totsiaw/proxma-patches/issues/29)

### New Features

* **olx:** remove the item-detail (PDP) banner ad slots ([82e20dd](https://github.com/totsiaw/proxma-patches/commit/82e20dd0a2c9182fbcd0c08f3aeff575fb8e176f))

## [1.18.1](https://github.com/totsiaw/proxma-patches/compare/1.18.0...1.18.1) (2026-10-20)

### Bug Fixes

* **netmonster:** bypass Google Play PairIP license check on 5.0.x ([b78b70c](https://github.com/totsiaw/proxma-patches/commit/b78b70c4c60607b6c394336e8a0df2cf5368f2d7))

## [1.18.0](https://github.com/totsiaw/proxma-patches/compare/1.17.0...1.18.0) (2026-10-20)

### Bug Fixes

* **netmonster:** expose the Google Maps key option in the patch list ([0a064e7](https://github.com/totsiaw/proxma-patches/commit/0a064e7103ef85140649eeac472178b5cb6ae3b9))
* **netmonster:** retarget premium patch to 5.0.0 / 5.0.1 ([3e9a6ab](https://github.com/totsiaw/proxma-patches/commit/3e9a6abc5466a7333fb51da70fe5239a028fb171))
* **simosa:** claim the daily check-in reward as soon as the state loads ([e9c07ad](https://github.com/totsiaw/proxma-patches/commit/e9c07ade230961213bb1f2d13ed8c35351075577))

### New Features

* **indrive:** add remove-ads patch for sinet.startup.inDriver 5.188.0 / 5.189.0 ([2c7a0a8](https://github.com/totsiaw/proxma-patches/commit/2c7a0a832496a2d221e250e8564cb6a167d16968))

## [1.17.0](https://github.com/totsiaw/proxma-patches/compare/1.16.1...1.17.0) (2026-10-17)

### New Features

* **olx:** add OLX India remove-ads and remove-Elite-promotions patches ([4632596](https://github.com/totsiaw/proxma-patches/commit/463259672e3467319a9011283b2cd97c7bf0a751)), closes [#24](https://github.com/totsiaw/proxma-patches/issues/24)

## [1.16.1](https://github.com/totsiaw/proxma-patches/compare/1.16.0...1.16.1) (2026-10-14)

### Bug Fixes

* foodpanda — build the toggle row without the app's classes ([5b3d0a1](https://github.com/totsiaw/proxma-patches/commit/5b3d0a1d240156b398d02ec1a418e40aa3df673e))
* foodpanda — ship the dark mode toggle's classes in the bundle ([0ad4af6](https://github.com/totsiaw/proxma-patches/commit/0ad4af65a100658be769ff390df37d2215ad8ff4))

## [1.16.0](https://github.com/totsiaw/proxma-patches/compare/1.15.1...1.16.0) (2026-10-14)

### Bug Fixes

* foodpanda — add second anti-tamper layer short-circuiting the block-screen orchestrator ([88e93d3](https://github.com/totsiaw/proxma-patches/commit/88e93d33e250e6472aba8a12676a0a02899f6434))
* foodpanda dark mode — empty FIELD_OVERRIDES so remapped fields actually darken ([2fcdeab](https://github.com/totsiaw/proxma-patches/commit/2fcdeabe648fafdbfabf532d35e1318517881fb3))
* storywise — block force-update prompt ([76b5bf9](https://github.com/totsiaw/proxma-patches/commit/76b5bf9ff6ba965d76eb26f2687185d46a0fe75e))
* storywise — embedded credential restore (sw_cred.json) in bypass login ([a16dac8](https://github.com/totsiaw/proxma-patches/commit/a16dac8abec927ef03f2d855498556d7a1a3c863))
* storywise — substitute Google sign-in with anonymous auth (A3) ([c3caba3](https://github.com/totsiaw/proxma-patches/commit/c3caba31289564a882e8041b5abd537b654fe8dc))
* storywise — use real signInAnonymously chain in bypass login ([7b4a129](https://github.com/totsiaw/proxma-patches/commit/7b4a129c9712ced0fdfa6db0c684fe883af0bf32))

### New Features

* foodpanda — dark mode toggle in settings ([ffa90b1](https://github.com/totsiaw/proxma-patches/commit/ffa90b1b8a9ab0992e1c1631a8fa6c700d6011fb))
* storywise — bypass login and unlock pass ([af36c44](https://github.com/totsiaw/proxma-patches/commit/af36c44897297f51c6cfafd5c611d4bddb61edd1))

## [1.15.1](https://github.com/totsiaw/proxma-patches/compare/1.15.0...1.15.1) (2026-10-13)

### Bug Fixes

* foodpanda — add second anti-tamper layer short-circuiting the block-screen orchestrator ([c7ffeb9](https://github.com/totsiaw/proxma-patches/commit/c7ffeb91bcd6b2883cc1ba35f10292d30d1dc949))

## [1.15.0](https://github.com/totsiaw/proxma-patches/compare/1.14.0...1.15.0) (2026-10-12)

### New Features

* foodpanda — remove home/reels ad banners, CrossSell popup, and forced review prompt ([d8df775](https://github.com/totsiaw/proxma-patches/commit/d8df775652a805e579e4cff95b4c6d59e361f4c5))

## [1.14.0](https://github.com/totsiaw/proxma-patches/compare/1.13.0...1.14.0) (2026-10-07)

### New Features

* add foodpanda order-tracking ad + PandaMart popup removal ([804b285](https://github.com/totsiaw/proxma-patches/commit/804b2854313bf446f327883158562aebd0d29877))
* add Safar (com.safar.fyi) ad removal ([299dc68](https://github.com/totsiaw/proxma-patches/commit/299dc68b66bdd3978784079923aef927605a5cc7))
* bypass PairIP license check for Safar re-signed builds ([e00e097](https://github.com/totsiaw/proxma-patches/commit/e00e0972c78b36b789b72a8fac1b89326d657fe9))
* update NetMonster premium unlock for 4.0.4 ([5ae6b63](https://github.com/totsiaw/proxma-patches/commit/5ae6b63e5dc8771c171f2d59632aae8622ea0c88))

## [1.13.0](https://github.com/totsiaw/proxma-patches/compare/1.12.1...1.13.0) (2026-09-30)

### New Features

* add foodpanda Bypass anti-tamper + Unlock premium patches ([73da778](https://github.com/totsiaw/proxma-patches/commit/73da778994bc5973d0bdd56fec7365c3ae9c9d03))
* **simosa:** remove day-10/20/30 check-in milestone RewardedAd ([8946da4](https://github.com/totsiaw/proxma-patches/commit/8946da4f1f764e29f6b120f6bad3f01f09be88f2))

## [1.12.1](https://github.com/totsiaw/proxma-patches/compare/1.12.0...1.12.1) (2026-09-21)

### Bug Fixes

* **netmonster:** retarget Unlock premium for 3.4.3 ([8beed38](https://github.com/totsiaw/proxma-patches/commit/8beed389993f2760daa2d859564d119a74d54cd9))

## [1.12.0](https://github.com/totsiaw/proxma-patches/compare/1.11.0...1.12.0) (2026-09-21)

### New Features

* **simosa:** split check-in ad removal into its own patch ([2797106](https://github.com/totsiaw/proxma-patches/commit/27971066a76b9ee8693588a6da389fd9fcbbb43e)), closes [morphe-manager#616](https://github.com/totsiaw/morphe-manager/issues/616) [#616](https://github.com/totsiaw/proxma-patches/issues/616)

## [1.11.0](https://github.com/totsiaw/proxma-patches/compare/1.10.0...1.11.0) (2026-09-21)

### New Features

* **olx:** add Remove ads patch ([02b00d9](https://github.com/totsiaw/proxma-patches/commit/02b00d968b6e0ec169bc1280775db9cc19a070d6))

## [1.10.0](https://github.com/totsiaw/proxma-patches/compare/1.9.0...1.10.0) (2026-09-21)

### New Features

* **olx:** add Force dark mode patch ([5cf94c1](https://github.com/totsiaw/proxma-patches/commit/5cf94c1481102a5eeff72e8cc7a6d362d5edf6bf))

## [1.9.0](https://github.com/totsiaw/proxma-patches/compare/1.8.0...1.9.0) (2026-09-21)

### Bug Fixes

* **simosa:** target Simosa 3.3.4.2 ([0363c02](https://github.com/totsiaw/proxma-patches/commit/0363c026d685ecc5e2b333f4e5b4dd04325aa727))

### New Features

* **simosa:** remove daily check-in / SocialPlus feed ads ([0c962f7](https://github.com/totsiaw/proxma-patches/commit/0c962f7b8c38cdb1e80e147b3d87defe2a97fd21))

## [1.8.0](https://github.com/totsiaw/proxma-patches/compare/1.7.0...1.8.0) (2026-07-19)

### New Features

* add mtproxy Remove ads patch ([a35a449](https://github.com/totsiaw/proxma-patches/commit/a35a449731fad72f7e7634cced08c7164fb631a9))

## [1.7.0](https://github.com/totsiaw/proxma-patches/compare/1.6.0...1.7.0) (2026-07-19)

### New Features

* add mytelenor Bypass device validation patch ([7d9a1a7](https://github.com/totsiaw/proxma-patches/commit/7d9a1a76c66a1bc436a9998ea3f18d5d1cb52b39))

## [1.6.0](https://github.com/totsiaw/proxma-patches/compare/1.5.1...1.6.0) (2026-07-17)

### New Features

* add mytelenor patches (Remove ads, Block trackers, manifest flags) ([47b6550](https://github.com/totsiaw/proxma-patches/commit/47b65503d78f254b9d8517051ef17dac33107e16))

## [1.5.1](https://github.com/totsiaw/proxma-patches/compare/1.5.0...1.5.1) (2026-07-14)

### Bug Fixes

* delist Daraz — remove patches (unusable on re-signed builds) ([e6cd42e](https://github.com/totsiaw/proxma-patches/commit/e6cd42ef5c4e43adb7fbab16d303b809d197740c))

## [1.5.0](https://github.com/totsiaw/proxma-patches/compare/1.4.1...1.5.0) (2026-07-13)

### New Features

* Investify unlock premium + PairIP bypass ([20fc9b5](https://github.com/totsiaw/proxma-patches/commit/20fc9b52bf743634826ee77eaca18bac91b1d880))
* NetMonster unlock premium ([d4de8f8](https://github.com/totsiaw/proxma-patches/commit/d4de8f8de83083d53c588436f8f1612cee81dbd5))

## [1.4.1](https://github.com/totsiaw/proxma-patches/compare/1.4.0...1.4.1) (2026-07-10)

### Bug Fixes

* mark Daraz patch as WIP ([70e51cd](https://github.com/totsiaw/proxma-patches/commit/70e51cdba01af8c738bb9b96156abc3e3523fa2a))

## [1.4.0](https://github.com/totsiaw/proxma-patches/compare/1.3.1...1.4.0) (2026-07-10)

### New Features

* Daraz (com.daraz.android) remove analytics & tracking ([4ddb59c](https://github.com/totsiaw/proxma-patches/commit/4ddb59c0e76e74845ca816ad464b01fb89639cdb))

## [1.3.1](https://github.com/totsiaw/proxma-patches/compare/1.3.0...1.3.1) (2026-07-10)

### Bug Fixes

* compile errors in unlock-daily-reward patch ([4628acc](https://github.com/totsiaw/proxma-patches/commit/4628acc9000d94f0bff8eb5180e0cff94723fcc0))
* MyZong daily reward — remove the popup too, claim directly ([578ef3d](https://github.com/totsiaw/proxma-patches/commit/578ef3d820c6375847ef48ee57eb21002be68362))

## [1.3.0](https://github.com/totsiaw/proxma-patches/compare/1.2.2...1.3.0) (2026-07-10)

### New Features

* MyZong — unlock daily reward without watching ad ([1bae4e5](https://github.com/totsiaw/proxma-patches/commit/1bae4e5ede5bbc2bd029b2d23108114b06bbeda9))

## [1.2.2](https://github.com/totsiaw/proxma-patches/compare/1.2.1...1.2.2) (2026-07-10)

### Bug Fixes

* MyZong — stub AppOpenAd (launch ad slipped past v1.2.1) ([a4113dc](https://github.com/totsiaw/proxma-patches/commit/a4113dce237c55d202b2c785b568ea9fa147a882))

## [1.2.1](https://github.com/totsiaw/proxma-patches/compare/1.2.0...1.2.1) (2026-07-09)

### Bug Fixes

* MyZong — fully kill AdMob (auto-init provider + all ad loads) ([5a092ad](https://github.com/totsiaw/proxma-patches/commit/5a092adb888015fb92d01e1cce1318387481f3fe))

## [1.2.0](https://github.com/totsiaw/proxma-patches/compare/1.1.0...1.2.0) (2026-07-09)

### New Features

* MyZong — stub AppsFlyer/TikTok SDK init too ([7e17a9c](https://github.com/totsiaw/proxma-patches/commit/7e17a9cd9b60c3af0d52f55ef08a5fb588512015))

## [1.1.0](https://github.com/totsiaw/proxma-patches/compare/1.0.0...1.1.0) (2026-07-09)

### New Features

* MyZong (com.zong.customercare) remove ads & tracking ([09513ad](https://github.com/totsiaw/proxma-patches/commit/09513ad8cf0fcd776eaaf24feefd4a207676897b))

## 1.0.0 (2026-07-09)

### New Features

* Proxma patches for Simosa (Jazz World) ([6d3db83](https://github.com/totsiaw/proxma-patches/commit/6d3db837b0c6c15591efc881ad221ff979c56359))
