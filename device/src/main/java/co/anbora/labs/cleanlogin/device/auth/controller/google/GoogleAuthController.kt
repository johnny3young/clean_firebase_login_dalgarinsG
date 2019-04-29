package co.anbora.labs.cleanlogin.device.auth.controller.google

import android.app.Activity
import android.content.Intent
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.domain.auth.AuthEnum
import co.anbora.labs.cleanlogin.domain.model.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class GoogleAuthController: AuthController {

    private val requestCode: Int
    private val data: Intent
    private val context: Activity

    //Firebase Auth
    private val mAuth: FirebaseAuth

    private val callback: AuthCallback

    constructor(activityResult: ActivityResult, mAuth: FirebaseAuth, callback: AuthCallback) {

        this.context = activityResult.context
        this.requestCode = activityResult.requestCode
        this.data = activityResult.data
        this.mAuth = mAuth

        this.callback = callback
    }

    override fun login() {
        if (this.requestCode == AuthEnum.GOOGLE.authValue) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                fireBaseAuthWithGoogle(account, callback)
            } catch (e: ApiException) {
                callback.onError()
            }
        }
    }

    private fun fireBaseAuthWithGoogle(acct: GoogleSignInAccount, callback: AuthCallback) {

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(context) { task ->
                    if (task.isSuccessful) {

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