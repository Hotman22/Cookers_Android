package com.otdev.cookers.authentication.domain.repository

import com.otdev.cookers.authentication.data.model.TokenDto
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    suspend fun logIn(userName: String, password: String): Result<TokenDto>
}