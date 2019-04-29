package co.anbora.labs.cleanlogin.device.auth.factory

import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import co.anbora.labs.cleanlogin.domain.auth.service.Auth

interface AuthFactory {

    fun getAuthService(): Auth

    fun getAuthController(activityResult: ActivityResult, callback: AuthCallback): AuthController

}