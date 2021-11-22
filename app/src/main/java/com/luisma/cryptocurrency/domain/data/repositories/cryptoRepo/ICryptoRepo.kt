package com.luisma.cryptocurrency.domain.data.repositories.cryptoRepo

import com.luisma.cryptocurrency.domain.data.models.cryptoModels.Crypto
import com.luisma.cryptocurrency.domain.data.models.cryptoModels.cryptoDetails.CryptoDetails
import com.luisma.cryptocurrency.domain.data.utils.models.ResponseDomain

interface ICryptoRepo {
    suspend fun getCyptos(): ResponseDomain<List<Crypto>>
    suspend fun getCryptoById(id: String): ResponseDomain<CryptoDetails>
}