package co.anbora.labs.cleanlogin.device.auth.controller.facebook

import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import com.facebook.CallbackManager

class FacebookAuthController: AuthController {

    private val mCallbackManager: CallbackManager
    private val activityResult: ActivityResult

    constructor(activityResult: ActivityResult, callbackManager: CallbackManager) {

        this.mCallbackManager = callbackManager
        this.activityResult = activityResult
    }

    override fun login() {

        this.mCallbackManager.onActivityResult(activityResult.requestCode, activityResult.resultCode, activityResult.data)
    }
}