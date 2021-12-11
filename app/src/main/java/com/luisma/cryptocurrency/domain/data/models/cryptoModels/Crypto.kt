package com.luisma.cryptocurrency.domain.data.models.cryptoModels


data class CryptoDomain(
    val lastUpdate: String,
    val cryptos: List<Crypto>
)

data class Crypto (
    val id: String,
    val isActive: Boolean,
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)