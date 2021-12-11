package com.luisma.cryptocurrency.domain.data.utils.models

enum class ErrorType {
    IO,
    Http,
    Unknow
}


sealed class ResponseDomain<Domain>(
    val domain: Domain?,
    val errorType: ErrorType? = null,
    val message: String = "",
) {
    class Success<Domain>(
        domain: Domain,
        message: String? = null,
    ) : ResponseDomain<Domain>(
        domain,
        message = message ?: ""
    )

    class Error<Domain>(
        doamin: Domain? = null,
        message: String,
        errorType: ErrorType = ErrorType.Unknow,
    ) : ResponseDomain<Domain>(
        domain = doamin,
        errorType = errorType,
        message = message)
}