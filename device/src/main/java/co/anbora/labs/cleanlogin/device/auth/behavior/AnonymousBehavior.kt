package co.anbora.labs.cleanlogin.device.auth.behavior

import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.function.Function

class AnonymousBehavior(val mAuth: FirebaseAuth, val callback: AuthCallback,
                        private val mapper: Function<FirebaseUser, User>) : AnonymousAuthBehavior {


    override fun onLoginComplete() {

        this.mAuth.signInAnonymously().addOnCompleteListener { task ->
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