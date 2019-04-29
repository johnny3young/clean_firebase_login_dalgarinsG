package co.anbora.labs.cleanlogin.device.auth.service.twitter

import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import co.anbora.labs.cleanlogin.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.TwitterAuthProvider
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.identity.TwitterLoginButton


class AuthTwitter : Auth {

    private val twitterLoginButton: TwitterLoginButton
    private val callback: AuthCallback
    private val mAuth: FirebaseAuth

    constructor(mAuth: FirebaseAuth, twitterLoginButton: TwitterLoginButton, callback: AuthCallback) {

        this.mAuth = mAuth
        this.twitterLoginButton = twitterLoginButton
        this.callback = callback
    }

    override fun loginRequest() {

        this.twitterLoginButton.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>?) {
                result?.data?.let {
                    handleTwitterSession(result.data)
                }
            }

            override fun failure(exception: TwitterException?) {
                callback.onError()
            }
        }
    }

    private fun handleTwitterSession(session: TwitterSession) {

        val credential = TwitterAuthProvider.getCredential(
                session.authToken.token,
                session.authToken.secret)

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener {
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