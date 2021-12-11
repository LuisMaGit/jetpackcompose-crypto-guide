package com.luisma.cryptocurrency.dataServices.network.dtos.cryptoDtos.cryptoDetailsDto


import com.google.gson.annotations.SerializedName

data class WhitepaperDto(
    @SerializedName("link")
    val link: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)