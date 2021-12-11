package com.luisma.cryptocurrency.domain.data.utils.helpers

import com.luisma.cryptocurrency.domain.data.utils.models.ErrorType
import com.luisma.cryptocurrency.domain.data.utils.models.ResponseDomain
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class SimpleResponseHandler @Inject constructor() {

    private val unknowErrorLog = "Response not successful"

    suspend fun <Domain, Dto> handleResponse(
        call: suspend () -> Response<Dto>,
        mapper: (Dto: Dto) -> Domain,
        errorTag: String = "",
    ): ResponseDomain<Domain> {
        try {
            val response = call()
            if (response.isSuccessful || response.body() != null) {
                val domain = mapper(response.body()!!)
                return ResponseDomain.Success(domain)

            }
            return ResponseDomain.Error(message = "$errorTag: $unknowErrorLog")

        } catch (e: HttpException) {
            return ResponseDomain.Error(
                message = "$errorTag: $e",
                errorType = ErrorType.Http
            )
        } catch (e: IOException) {
            return ResponseDomain.Error(
                message = "$errorTag: $e",
                errorType = ErrorType.IO
            )
        }
    }

    suspend fun <Domain, Entity, Dto> handleResponseWithCache(
        call: suspend () -> Response<Dto>,
        mapDtoToEntity: (dto: Dto) -> Entity,
        saveDb: suspend (entityToDb: Entity) -> Unit,
        getDb: suspend () -> Entity,
        mapEntityToDomain: (entityFromDb: Entity) -> Domain,
        errorTag: String = "",
    ): ResponseDomain<Domain> {
        try {
            val response = call()
            //RESPONSE FROM SERVER
            if (response.isSuccessful || response.body() != null) {
                val entityToDb = mapDtoToEntity(response.body()!!)
                saveDb(entityToDb)
                val entityFromDb = getDb()
                val domain = mapEntityToDomain(entityFromDb)
                return ResponseDomain.Success(domain)
            }
            //ERROR
            val entityFromDb = getDb()
            val domain = mapEntityToDomain(entityFromDb)
            return ResponseDomain.Error(
                doamin = domain,
                message = "$errorTag: $unknowErrorLog"
            )
        } catch (e: HttpException) {
            val entityFromDb = getDb()
            val domain = mapEntityToDomain(entityFromDb)
            return ResponseDomain.Error(
                doamin = domain,
                errorType = ErrorType.Http,
                message = "$errorTag: $e"
            )
        } catch (e: IOException) {
            val entityFromDb = getDb()
            val domain = mapEntityToDomain(entityFromDb)
            return ResponseDomain.Error(
                doamin = domain,
                errorType = ErrorType.IO,
                message = "$errorTag: $e"
            )
        }
    }
}