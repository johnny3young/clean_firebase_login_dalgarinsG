package co.anbora.labs.cleanlogin.device.auth.model

import android.app.Activity
import com.google.firebase.auth.PhoneAuthProvider

data class AuthPhoneRequest(val phoneNumber: String,
                            val context: Activity,
                            val phoneAuthCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks) {
}