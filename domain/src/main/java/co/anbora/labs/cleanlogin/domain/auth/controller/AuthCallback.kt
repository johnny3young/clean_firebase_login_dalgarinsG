package co.anbora.labs.cleanlogin.domain.auth.controller

import co.anbora.labs.cleanlogin.domain.model.User

interface AuthCallback {

    fun onSuccess(user: User)

    fun onError()

}