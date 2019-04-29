package co.anbora.labs.cleanlogin.device.auth.behavior

import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.model.User
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.function.Function

class CredentialBehavior(val mAuth: FirebaseAuth, val callback: AuthCallback,
                         private val mapper: Function<FirebaseUser, User>) : AuthBehavior {

    override fun onLoginComplete(credential: AuthCredential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        mAuth.currentUser?.let {
                            callback.onSuccess(mapper.apply(it))
                        }
                    } else {
                        callback.onError()
                    }
                }
    }
}