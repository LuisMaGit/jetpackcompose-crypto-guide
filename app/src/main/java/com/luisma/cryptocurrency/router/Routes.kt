package com.luisma.cryptocurrency.router

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object Routes {
    val Home = object : INavigationCommand {
        override val route: String
            get() = "Home"
        override val arguments: List<NamedNavArgument>
            get() = listOf()
    }

    object CryptoDetails {
        private const val BASE_ROUTE = "CryptoDetails"
        const val CRYPTO_ID = "crypto_id"
        const val ROUTE = "$BASE_ROUTE/{$CRYPTO_ID}"
        val ARGUMENTS: List<NamedNavArgument> = listOf(navArgument(name = CRYPTO_ID) {
            type = NavType.StringType
        })

        fun goToCrypto(cryptoId: String) = object : INavigationCommand {
            override val route: String
                get() = "$BASE_ROUTE/$cryptoId"
            override val arguments: List<NamedNavArgument>
                get() = ARGUMENTS
        }
    }
}