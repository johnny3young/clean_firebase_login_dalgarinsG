package co.anbora.labs.cleanlogin.device.auth.behavior

import com.google.firebase.auth.AuthCredential

interface AuthBehavior {

    fun onLoginComplete(credential: AuthCredential)

}