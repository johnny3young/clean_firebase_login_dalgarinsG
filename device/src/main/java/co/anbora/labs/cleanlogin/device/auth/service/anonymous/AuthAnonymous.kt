package co.anbora.labs.cleanlogin.device.auth.service.anonymous

import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import co.anbora.labs.cleanlogin.domain.model.User
import com.google.firebase.auth.FirebaseAuth

class AuthAnonymous : Auth {

    private val mAuth: FirebaseAuth
    private val callback: AuthCallback

    constructor(mAuth: FirebaseAuth, callback: AuthCallback) {
        this.mAuth = mAuth
        this.callback = callback
    }

    override fun loginRequest() {
        this.mAuth.signInAnonymously().addOnCompleteListener {
            if (it.isSuccessful) {

                mAuth.currentUser?.let {
                    val user = User.Builder()
                            .id(it.uid)
                            .name(it.displayName)
                            .build()
                    callback.onSuccess(user)
                }
            } else {

                callback.onError()
            }
        }
    }
}