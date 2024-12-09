package com.otdev.cookers.authentication.data.datasource.remote

import com.otdev.cookers.authentication.data.model.LoginRequest
import com.otdev.cookers.authentication.data.model.TokenDto
import com.otdev.cookers.core.utils.NetworkError
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

internal class AuthenticationNetworkImpl @Inject constructor(
    private val authenticationApi: AuthenticationApi,
) : AuthenticationNetwork {
    override suspend fun logIn(userName: String, password: String): Result<TokenDto> =
        runCatching {
            val request = LoginRequest(userName, password)
            authenticationApi.logIn(request)
        }.fold(
            onFailure = { throwable ->
                val error = when (throwable) {
                    is HttpException -> {
                        when (throwable.code()) {
                            401 -> NetworkError.UnauthorizedException()
                            else -> NetworkError.UnknownErrorException()
                        }
                    }

                    is IOException -> NetworkError.NetworkUnavailableException()
                    else -> NetworkError.UnknownErrorException()
                }
                Result.failure(error)
            },
            onSuccess = { result ->
                Result.success(result)
            })
}