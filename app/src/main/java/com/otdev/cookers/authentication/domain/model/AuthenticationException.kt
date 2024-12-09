package com.otdev.cookers.authentication.domain.model

sealed class AuthenticationException(message: String): Exception(message) {
    class UnauthorizedException : AuthenticationException("Unauthorized access")
    class UnknownErrorException : AuthenticationException("UnknownErrorException")
}