package com.otdev.cookers.authentication.data.repository

import com.otdev.cookers.authentication.data.datasource.remote.AuthenticationNetwork
import com.otdev.cookers.authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

internal class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationNetwork: AuthenticationNetwork,
) : AuthenticationRepository {

    override suspend fun logIn(userName: String, password: String) =
        authenticationNetwork.logIn(userName, password).onSuccess {
            //TODO save token in encryptedsharedpref for next login
            Result.success(Unit)
        }
}