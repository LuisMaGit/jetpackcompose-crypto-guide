package com.luisma.cryptocurrency.dataServices.network.entities.cryptoEntities.cryptoDetailsEntity


import com.google.gson.annotations.SerializedName

data class TeamEntity(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("position")
    val position: String
)