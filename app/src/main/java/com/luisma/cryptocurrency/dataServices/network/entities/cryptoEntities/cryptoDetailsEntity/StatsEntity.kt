package com.luisma.cryptocurrency.dataServices.network.entities.cryptoEntities.cryptoDetailsEntity


import com.google.gson.annotations.SerializedName

data class StatsEntity(
    @SerializedName("contributors")
    val contributors: Int,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("stars")
    val stars: Int,
    @SerializedName("subscribers")
    val subscribers: Int
)