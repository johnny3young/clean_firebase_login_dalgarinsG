package co.anbora.labs.cleanlogin.device.auth.controller.phone

import co.anbora.labs.cleanlogin.device.auth.behavior.AuthBehavior
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential


class PhoneAuthController : AuthController {

    private val mAuth: FirebaseAuth
    private val credential: PhoneAuthCredential
    private val authBehavior: AuthBehavior

    constructor(mAuth: FirebaseAuth, credential: PhoneAuthCredential, authBehavior: AuthBehavior) {
        this.mAuth = mAuth
        this.credential = credential
        this.authBehavior = authBehavior
    }

    override fun login() {
        authBehavior.onLoginComplete(credential)
    }
}