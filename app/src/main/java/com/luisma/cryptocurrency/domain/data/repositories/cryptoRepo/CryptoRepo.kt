package com.luisma.cryptocurrency.domain.data.repositories.cryptoRepo

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.luisma.cryptocurrency.dataServices.internal.source.ICryptoLocal
import com.luisma.cryptocurrency.domain.data.utils.models.ResponseDomain
import com.luisma.cryptocurrency.dataServices.network.api.ICryptoApi
import com.luisma.cryptocurrency.domain.data.mappers.CryptoDetailsMapper
import com.luisma.cryptocurrency.domain.data.mappers.CryptoMapper
import com.luisma.cryptocurrency.domain.data.models.cryptoModels.CryptoDomain
import com.luisma.cryptocurrency.domain.data.models.cryptoModels.cryptoDetails.CryptoDetails
import com.luisma.cryptocurrency.domain.data.utils.helpers.SimpleResponseHandler
import com.luisma.cryptocurrency.domain.data.utils.helpers.parseDateTime
import java.time.LocalDateTime

class CryptoRepo constructor(
    private val cryptoApi: ICryptoApi,
    private val cryptoLocal: ICryptoLocal,
    private val cryptoMapper: CryptoMapper,
    private val cryptoDetailsMapper: CryptoDetailsMapper,
    private val simpleResponseHandler: SimpleResponseHandler,
) : ICryptoRepo {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun fetchAndCacheCryptos(): ResponseDomain<CryptoDomain>{
        val response = simpleResponseHandler.handleResponseWithCache(
            call = {
                cryptoApi.getCoins()
            },
            mapDtoToEntity = {
                cryptoMapper.mapDtosToEntities(it)
            },
            saveDb = {
                cryptoLocal.insertCryptos(it)
            },
            getDb = {
                cryptoLocal.getCryptos()
            },
            mapEntityToDomain = {
                cryptoMapper.mapEntitiesToDomain(it)
            },
            errorTag = "Cryptos"
        )

        val lastUpdate = cryptoLocal.insertLastUpdateCrypto(
            parseDateTime(LocalDateTime.now())
        )
        return ResponseDomain.Success(
            domain = CryptoDomain(
                cryptos = response.domain ?: listOf(),
                lastUpdate = lastUpdate
            )
        )
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getCyptos(): ResponseDomain<CryptoDomain> {
        //USING DB CRYPTOS
        val storedCryptosEntity = cryptoLocal.getCryptos()
        if (storedCryptosEntity.isNotEmpty()) {
            val lastUpdate = cryptoLocal.getLastUpdateCryptos()
            val storedCryptosDomain = cryptoMapper.mapEntitiesToDomain(storedCryptosEntity)
            return ResponseDomain.Success(
                domain = CryptoDomain(
                    cryptos = storedCryptosDomain,
                    lastUpdate = lastUpdate
                )
            )
        }

        //FETCHING AND CACHING
        return fetchAndCacheCryptos()
    }

    override suspend fun getCryptoById(id: String): ResponseDomain<CryptoDetails> {
        return simpleResponseHandler.handleResponse(
            call = {
                cryptoApi.getCoinById(id)
            },
            mapper = {
                cryptoDetailsMapper.mapSourceToDomain(it)
            },
            errorTag = "Crypto Details"
        )
    }
}