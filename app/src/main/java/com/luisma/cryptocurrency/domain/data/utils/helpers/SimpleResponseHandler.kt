package com.luisma.cryptocurrency.domain.data.utils.helpers

import com.luisma.cryptocurrency.domain.data.utils.models.ResponseDomain
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class SimpleResponseHandler @Inject constructor() {

    suspend fun <Domain, Entity> handleResponse(
        call: suspend () -> Response<Entity>,
        mapper: (enitity: Entity) -> Domain,
        tag: String = "",
    ): ResponseDomain<Domain> {
        try {
            val response = call()
            if (response.isSuccessful || response.body() != null) {
                val domain = mapper(response.body()!!)
                return ResponseDomain.Success(domain)

            }
            return ResponseDomain.Error(message = "$tag: Response not succesful")

        } catch (e: Exception) {
            return ResponseDomain.Error(message = "$tag: $e")
        }
    }
}