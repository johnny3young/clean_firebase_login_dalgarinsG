package co.anbora.labs.cleanlogin.device.auth.factory.facebook

import co.anbora.labs.cleanlogin.device.auth.controller.facebook.FacebookAuthController
import co.anbora.labs.cleanlogin.device.auth.factory.AuthFactory
import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.device.auth.service.facebook.AuthFacebook
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import com.facebook.CallbackManager
import com.google.firebase.auth.FirebaseAuth

class AuthFacebookFactory : AuthFactory {

    private val authService: Auth
    private val callbackManager: CallbackManager

    constructor(mAuth: FirebaseAuth, callbackManager: CallbackManager, callback: AuthCallback) {
        authService = AuthFacebook(mAuth, callbackManager, callback)
        this.callbackManager = callbackManager
    }

    override fun getAuthService(): Auth {
        return authService
    }

    override fun getAuthController(activityResult: ActivityResult, callback: AuthCallback): AuthController {
        return FacebookAuthController(activityResult, callbackManager)
    }
}