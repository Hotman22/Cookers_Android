package com.otdev.cookers.authentication.data.datasource.remote

import com.otdev.cookers.authentication.data.model.TokenDto

internal interface AuthenticationNetwork {
    suspend fun logIn(userName: String, password: String): Result<TokenDto>
}
