package com.otdev.cookers.authentication.data.datasource.remote

import com.otdev.cookers.authentication.data.model.LoginRequest
import com.otdev.cookers.authentication.data.model.TokenDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {

    @POST("authentication/login")
    suspend fun logIn(@Body loginRequest: LoginRequest): TokenDto
}