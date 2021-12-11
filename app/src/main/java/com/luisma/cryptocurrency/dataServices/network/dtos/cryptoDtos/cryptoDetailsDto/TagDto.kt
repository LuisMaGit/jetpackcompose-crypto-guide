package com.luisma.cryptocurrency.dataServices.network.dtos.cryptoDtos.cryptoDetailsDto


import com.google.gson.annotations.SerializedName

data class TagDto(
    @SerializedName("coin_counter")
    val coinCounter: Int,
    @SerializedName("ico_counter")
    val icoCounter: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)