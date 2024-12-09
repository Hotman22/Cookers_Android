package com.otdev.cookers.authentication.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TokenDto(
    val userId: String,
    val token: String,
    val refreshToken: String,
)