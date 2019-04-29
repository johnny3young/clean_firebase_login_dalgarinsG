package co.anbora.labs.cleanlogin.domain.auth.service

import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController

interface Auth {

    fun loginRequest()

    fun login(controller: AuthController) {
        controller.login()
    }

}