package com.luisma.cryptocurrency.dataServices.internal.source

import cryptodb.CryptoEntity

interface ICryptoLocal {
    suspend fun getCryptos(): List<CryptoEntity>
    suspend fun insertCryptos(entities: List<CryptoEntity> ): List<CryptoEntity>
    suspend fun getLastUpdateCryptos(): String
    suspend fun insertLastUpdateCrypto(date: String): String
}