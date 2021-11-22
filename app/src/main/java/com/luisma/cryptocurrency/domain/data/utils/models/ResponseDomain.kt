package com.luisma.cryptocurrency.domain.data.utils.models

sealed class ResponseDomain<Domain>(
    val domain: Domain?,
    val message: String = ""
) {
    class Success<Domain>(domain: Domain) : ResponseDomain<Domain>(domain)
    class Error<Nothing>(message: String) :
        ResponseDomain<Nothing>(domain = null, message = message)
}