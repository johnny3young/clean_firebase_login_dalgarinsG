package co.anbora.labs.cleanlogin.device.auth.service.facebook

import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import co.anbora.labs.cleanlogin.domain.model.User
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth


class AuthFacebook: Auth {

    private val facebookCallbackManager: CallbackManager
    private val callback: AuthCallback
    private val mAuth: FirebaseAuth

    constructor(mAuth: FirebaseAuth, callbackManager: CallbackManager, callback: AuthCallback) {

        this.mAuth = mAuth
        this.facebookCallbackManager = callbackManager
        this.callback = callback
    }

    override fun loginRequest() {
        LoginManager.getInstance().registerCallback(facebookCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                result?.accessToken?.let {
                    handleFacebookAccessToken(it, callback)
                }
            }

            override fun onCancel() {
                callback.onError()
            }

            override fun onError(error: FacebookException?) {
                callback.onError()
            }
        })
    }

    private fun handleFacebookAccessToken(token: AccessToken, callback: AuthCallback) {

        val credential = FacebookAuthProvider.getCredential(token.token)
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