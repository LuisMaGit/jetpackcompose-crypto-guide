package com.luisma.cryptocurrency.domain.data.repositories.cryptoRepo

import com.luisma.cryptocurrency.domain.data.utils.models.ResponseDomain
import com.luisma.cryptocurrency.dataServices.network.api.ICryptoApi
import com.luisma.cryptocurrency.dataServices.network.entities.cryptoEntities.cryptoDetailsEntity.CryptoDetailsEntity
import com.luisma.cryptocurrency.domain.data.mappers.CryptoDetailsMapper
import com.luisma.cryptocurrency.domain.data.mappers.CryptoMapper
import com.luisma.cryptocurrency.domain.data.models.cryptoModels.Crypto
import com.luisma.cryptocurrency.domain.data.models.cryptoModels.cryptoDetails.CryptoDetails
import com.luisma.cryptocurrency.domain.data.utils.helpers.SimpleResponseHandler

class CryptoRepo constructor(
    private val cryptoApi: ICryptoApi,
    private val cryptoMapper: CryptoMapper,
    private val cyptoDetailsMapper: CryptoDetailsMapper,
    private val simpleResponseHandler: SimpleResponseHandler,
) : ICryptoRepo {
    override suspend fun getCyptos(): ResponseDomain<List<Crypto>> {
        return simpleResponseHandler.handleResponse(
            call = {
                cryptoApi.getCoins()
            },
            mapper = {
                cryptoMapper.mapEntitiesToDomains(it)
            },
            tag = "Cryptos"
        )
    }

    override suspend fun getCryptoById(id: String): ResponseDomain<CryptoDetails> {
        return simpleResponseHandler.handleResponse(
            call = {
                cryptoApi.getCoinById(id)
            },
            mapper = {
                cyptoDetailsMapper.mapEntityToDomain(it)
            },
            tag = "Crypto Details"
        )
    }
}