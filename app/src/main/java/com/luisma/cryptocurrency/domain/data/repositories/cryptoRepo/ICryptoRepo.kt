package com.luisma.cryptocurrency.domain.data.repositories.cryptoRepo

import com.luisma.cryptocurrency.domain.data.models.cryptoModels.CryptoDomain
import com.luisma.cryptocurrency.domain.data.models.cryptoModels.cryptoDetails.CryptoDetails
import com.luisma.cryptocurrency.domain.data.utils.models.ResponseDomain

interface ICryptoRepo {
    suspend fun fetchAndCacheCryptos(): ResponseDomain<CryptoDomain>
    suspend fun getCyptos(): ResponseDomain<CryptoDomain>
    suspend fun getCryptoById(id: String): ResponseDomain<CryptoDetails>
}