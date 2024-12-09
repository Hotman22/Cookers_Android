package com.otdev.cookers.authentication.domain.usecase

import com.otdev.cookers.authentication.domain.model.AuthenticationException
import com.otdev.cookers.authentication.domain.repository.AuthenticationRepository
import com.otdev.cookers.authentication.domain.usecase.interfaces.LogIn
import com.otdev.cookers.core.utils.CoroutineContextProvider
import com.otdev.cookers.core.utils.NetworkError
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class LogInImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val coroutineContextProvider: CoroutineContextProvider,
) : LogIn {

    override suspend operator fun invoke(userName: String, password: String) =
        withContext(coroutineContextProvider.io) {
            authenticationRepository.logIn(userName, password).fold(
                onSuccess = {
                    Result.success(Unit)
                },
                onFailure = { exception ->
                    Result.failure(mapToAuthException(exception as NetworkError))
                })
        }

    private fun mapToAuthException(errorType: NetworkError): AuthenticationException {
        return when (errorType) {
            NetworkError.UnauthorizedException() -> AuthenticationException.UnauthorizedException()
            else -> AuthenticationException.UnknownErrorException()
        }
    }
}