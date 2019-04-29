package co.anbora.labs.cleanlogin.device.auth.factory.google

import android.app.Activity
import co.anbora.labs.cleanlogin.device.auth.controller.google.GoogleAuthController
import co.anbora.labs.cleanlogin.device.auth.service.google.AuthGoogle
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import co.anbora.labs.cleanlogin.device.auth.factory.AuthFactory
import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import com.google.firebase.auth.FirebaseAuth

class AuthGoogleFactory : AuthFactory {

    private val authService: Auth
    private val mAuth: FirebaseAuth

    constructor(context: Activity, defaultWebClientId: String, mAuth: FirebaseAuth) {
        authService = AuthGoogle(context, defaultWebClientId)
        this.mAuth = mAuth
    }

    override fun getAuthService(): Auth {
        return authService
    }

    override fun getAuthController(activityResult: ActivityResult, callback: AuthCallback): AuthController {
        return GoogleAuthController(activityResult, mAuth, callback)
    }
}