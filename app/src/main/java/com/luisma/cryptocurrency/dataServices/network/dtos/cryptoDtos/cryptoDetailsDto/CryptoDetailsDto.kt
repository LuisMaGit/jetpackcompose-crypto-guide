package com.luisma.cryptocurrency.dataServices.network.dtos.cryptoDtos.cryptoDetailsDto


import com.google.gson.annotations.SerializedName

data class CryptoDetailsDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("symbol")
    val symbol: String,

    @SerializedName("rank")
    val rank: Int,

    @SerializedName("is_new")
    val isNew: Boolean,

    @SerializedName("is_active")
    val isActive: Boolean,

    @SerializedName("type")
    val type: String,

    @SerializedName("tags")
    val tags: List<TagDto>,


    @SerializedName("team")
    val team: List<TeamDto>,

    @SerializedName("description")
    val description: String,

    @SerializedName("message")
    val message: String,

    @SerializedName("open_source")
    val openSource: Boolean,

    @SerializedName("started_at")
    val startedAt: String,

    @SerializedName("development_status")
    val developmentStatus: String,

    @SerializedName("hardware_wallet")
    val hardwareWallet: Boolean,

    @SerializedName("proof_type")
    val proofType: String,

    @SerializedName("org_structure")
    val orgStructure: String,

    @SerializedName("hash_algorithm")
    val hashAlgorithm: String,

    @SerializedName("links")
    val links: LinksDto,

    @SerializedName("links_extended")
    val linksExtended: List<LinksExtendedDto>,

    @SerializedName("whitepaper")
    val whitepaper: WhitepaperDto,

    @SerializedName("first_data_at")
    val firstDataAt: String,

    @SerializedName("last_data_at")
    val lastDataAt: String,
)