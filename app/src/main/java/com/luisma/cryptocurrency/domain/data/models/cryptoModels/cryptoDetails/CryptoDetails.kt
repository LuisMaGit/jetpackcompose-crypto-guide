package com.luisma.cryptocurrency.domain.data.models.cryptoModels.cryptoDetails


data class CryptoDetails (
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val isNew: Boolean,
    val isActive: Boolean,
    val description: String,
    val tags: List<Tag>,
    val team: List<Team>,
)