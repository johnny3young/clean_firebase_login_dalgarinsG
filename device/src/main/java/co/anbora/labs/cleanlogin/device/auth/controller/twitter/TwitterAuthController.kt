package co.anbora.labs.cleanlogin.device.auth.controller.twitter

import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import com.twitter.sdk.android.core.identity.TwitterLoginButton

class TwitterAuthController : AuthController {

    private val activityResult: ActivityResult
    private val twitterLoginButton: TwitterLoginButton

    constructor(activityResult: ActivityResult, twitterLoginButton: TwitterLoginButton) {

        this.twitterLoginButton = twitterLoginButton
        this.activityResult = activityResult
    }

    override fun login() {
        this.twitterLoginButton.onActivityResult(activityResult.requestCode, activityResult.resultCode, activityResult.data)
    }
}