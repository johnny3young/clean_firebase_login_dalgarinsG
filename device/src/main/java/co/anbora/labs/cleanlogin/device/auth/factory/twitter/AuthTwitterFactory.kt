package co.anbora.labs.cleanlogin.device.auth.factory.twitter

import co.anbora.labs.cleanlogin.device.auth.controller.twitter.TwitterAuthController
import co.anbora.labs.cleanlogin.device.auth.factory.AuthFactory
import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.device.auth.service.twitter.AuthTwitter
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import com.google.firebase.auth.FirebaseAuth
import com.twitter.sdk.android.core.identity.TwitterLoginButton

class AuthTwitterFactory : AuthFactory {

    private val authService: Auth
    private val twitterLoginButton: TwitterLoginButton

    constructor(mAuth: FirebaseAuth, twitterLoginButton: TwitterLoginButton, callback: AuthCallback) {
        this.authService = AuthTwitter(mAuth, twitterLoginButton, callback)
        this.twitterLoginButton = twitterLoginButton
    }

    override fun getAuthService(): Auth {
        return this.authService
    }

    override fun getAuthController(activityResult: ActivityResult, callback: AuthCallback): AuthController {
        return TwitterAuthController(activityResult, twitterLoginButton)
    }
}