package com.luisma.cryptocurrency.dataServices.network.constants

object NetworkConstants {
    object Paths {
        const val coinId = "coin_id"
    }

    object Routes {
        const val baseRoute = "https://api.coinpaprika.com/"
        const val getCryptos = "/v1/coins"
        const val getCryptosById = "$getCryptos/{${NetworkConstants.Paths.coinId}}"
    }
}