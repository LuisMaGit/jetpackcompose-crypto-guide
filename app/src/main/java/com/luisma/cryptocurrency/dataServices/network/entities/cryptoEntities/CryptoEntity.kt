package com.luisma.cryptocurrency.dataServices.network.entities.cryptoEntities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class CryptoEntity(
    @SerializedName("id")
    @Expose
    val id: String,

    @SerializedName("is_active")
    @Expose
    val isActive: Boolean,

    @SerializedName("is_new")
    @Expose
    val isNew: Boolean,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("rank")
    @Expose
    val rank: Int,

    @SerializedName("symbol")
    @Expose
    val symbol: String,

    @SerializedName("type")
    @Expose
    val type: String
)