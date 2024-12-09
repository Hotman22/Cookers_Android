package com.otdev.cookers.core.utils

sealed class NetworkError(message: String): Exception(message) {
    class UnauthorizedException : NetworkError("Unauthorized access")
    class NetworkUnavailableException : NetworkError("No internet connection")
    class UnknownErrorException : NetworkError("UnknownErrorException")
}