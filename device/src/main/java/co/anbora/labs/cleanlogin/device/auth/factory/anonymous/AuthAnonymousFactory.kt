package co.anbora.labs.cleanlogin.device.auth.factory.anonymous

import co.anbora.labs.cleanlogin.device.auth.controller.anonymous.AnonymousAuthController
import co.anbora.labs.cleanlogin.device.auth.factory.AuthFactory
import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.device.auth.service.anonymous.AuthAnonymous
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import com.google.firebase.auth.FirebaseAuth

class AuthAnonymousFactory : AuthFactory {

    private val mAuth: FirebaseAuth
    private val callback: AuthCallback

    constructor(mAuth: FirebaseAuth, callback: AuthCallback) {
        this.mAuth = mAuth
        this.callback = callback
    }

    override fun getAuthService(): Auth {
        return AuthAnonymous(mAuth, callback)
    }

    override fun getAuthController(activityResult: ActivityResult, callback: AuthCallback): AuthController {
        return AnonymousAuthController()
    }
}