package co.anbora.labs.cleanlogin.device.auth.service.phone

import android.app.Activity
import co.anbora.labs.cleanlogin.device.auth.model.AuthPhoneRequest
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class AuthPhone : Auth {

    private val phoneNumber: String
    private val context: Activity
    private val mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    constructor(authPhoneRequest: AuthPhoneRequest) {
        this.phoneNumber = authPhoneRequest.phoneNumber
        this.context = authPhoneRequest.context
        this.mCallbacks = authPhoneRequest.phoneAuthCallback
    }

    override fun loginRequest() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                context,
                mCallbacks)
    }
}