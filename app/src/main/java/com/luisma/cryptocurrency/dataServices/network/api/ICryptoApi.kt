package com.luisma.cryptocurrency.dataServices.network.api

import com.luisma.cryptocurrency.dataServices.network.constants.NetworkConstants
import com.luisma.cryptocurrency.dataServices.network.dtos.cryptoDtos.CryptoDto
import com.luisma.cryptocurrency.dataServices.network.dtos.cryptoDtos.cryptoDetailsDto.CryptoDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ICryptoApi {

    @GET(NetworkConstants.Routes.getCryptos)
    suspend fun getCoins(): Response<List<CryptoDto>>

    @GET(NetworkConstants.Routes.getCryptosById)
    suspend fun getCoinById(@Path(NetworkConstants.Paths.coinId) id: String): Response<CryptoDetailsDto>

}