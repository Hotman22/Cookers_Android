package com.otdev.cookers.authentication.domain.usecase.interfaces

import com.otdev.cookers.authentication.data.model.TokenDto
import kotlinx.coroutines.flow.Flow

interface LogIn {
    suspend operator fun invoke(userName: String, password: String): Result<Unit>
}