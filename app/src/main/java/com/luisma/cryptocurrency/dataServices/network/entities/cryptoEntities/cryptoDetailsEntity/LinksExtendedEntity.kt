package com.luisma.cryptocurrency.dataServices.network.entities.cryptoEntities.cryptoDetailsEntity


import com.google.gson.annotations.SerializedName

data class LinksExtendedEntity(
    @SerializedName("stats")
    val stats: StatsEntity,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)