package com.cleanroute.app.core.util

sealed class AppFailure(open val message: String) {
    data class DatabaseFailure(override val message: String) : AppFailure(message)
    data class ValidationFailure(override val message: String) : AppFailure(message)
    data class NotFoundFailure(override val message: String) : AppFailure(message)
    data class InvalidStateFailure(override val message: String) : AppFailure(message)
    data class UnknownFailure(override val message: String) : AppFailure(message)
}

sealed class Result<out T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Failure(val error: AppFailure) : Result<Nothing>()
}
