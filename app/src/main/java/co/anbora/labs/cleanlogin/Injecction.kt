package co.anbora.labs.cleanlogin

import android.app.Activity
import co.anbora.labs.cleanlogin.device.auth.behavior.AuthBehavior
import co.anbora.labs.cleanlogin.device.auth.behavior.CredentialBehavior
import co.anbora.labs.cleanlogin.device.auth.factory.AuthFactory
import co.anbora.labs.cleanlogin.device.auth.factory.anonymous.AuthAnonymousFactory
import co.anbora.labs.cleanlogin.device.auth.factory.facebook.AuthFacebookFactory
import co.anbora.labs.cleanlogin.device.auth.factory.google.AuthGoogleFactory
import co.anbora.labs.cleanlogin.device.auth.factory.phone.AuthPhoneFactory
import co.anbora.labs.cleanlogin.device.auth.factory.twitter.AuthTwitterFactory
import co.anbora.labs.cleanlogin.device.auth.mapper.UserMapper
import co.anbora.labs.cleanlogin.device.auth.model.AuthPhoneRequest
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.ui.InfoUserCallback
import co.anbora.labs.cleanlogin.ui.dialog.RequestDialog
import co.anbora.labs.cleanlogin.ui.dialog.phone.PhoneRequestDialog
import com.facebook.CallbackManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.twitter.sdk.android.core.identity.TwitterLoginButton

fun provideGoogleAuthFactory(context: Activity, defaultWebClientId: String, mAuth: FirebaseAuth): AuthFactory {
    return AuthGoogleFactory(context, defaultWebClientId, mAuth)
}

fun provideFacebookAuthFactory(mAuth: FirebaseAuth, callbackManager: CallbackManager, callback: AuthCallback): AuthFactory {
    return AuthFacebookFactory(mAuth, callbackManager, callback)
}

fun provideTwitterAuthFactory(mAuth: FirebaseAuth, twitterLoginButton: TwitterLoginButton, callback: AuthCallback): AuthFactory {
    return AuthTwitterFactory(mAuth, twitterLoginButton, callback)
}

fun provideAnonymousAuthFactory(mAuth: FirebaseAuth, callback: AuthCallback): AuthFactory {
    return AuthAnonymousFactory(mAuth, callback)
}

fun provideUserCallback(): AuthCallback {
    return InfoUserCallback()
}

fun provideAuthBehavior(mAuth: FirebaseAuth, callback: AuthCallback, mapper: UserMapper): AuthBehavior {
    return CredentialBehavior(mAuth, callback, mapper)
}

fun providePhoneAuthFactory(mAuth: FirebaseAuth,
                            authPhoneRequest: AuthPhoneRequest,
                            phoneAuthProvider: PhoneAuthProvider,
                            credential: PhoneAuthCredential,
                            authBehavior: AuthBehavior): AuthFactory {
    return AuthPhoneFactory(mAuth, authPhoneRequest, phoneAuthProvider, credential, authBehavior)
}

fun providePhoneRequestDialog(context: Activity): RequestDialog {
    return PhoneRequestDialog(context)
}

