package com.luisma.cryptocurrency.dataServices.network.dtos.cryptoDtos.cryptoDetailsDto


import com.google.gson.annotations.SerializedName

data class StatsDto(
    @SerializedName("contributors")
    val contributors: Int,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("stars")
    val stars: Int,
    @SerializedName("subscribers")
    val subscribers: Int
)