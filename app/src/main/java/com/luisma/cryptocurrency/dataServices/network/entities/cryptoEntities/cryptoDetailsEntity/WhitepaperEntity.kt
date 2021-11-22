package com.luisma.cryptocurrency.dataServices.network.entities.cryptoEntities.cryptoDetailsEntity


import com.google.gson.annotations.SerializedName

data class WhitepaperEntity(
    @SerializedName("link")
    val link: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)