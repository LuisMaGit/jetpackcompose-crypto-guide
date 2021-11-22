package com.luisma.cryptocurrency.di

import com.luisma.cryptocurrency.domain.data.mappers.CryptoMapper
import com.luisma.cryptocurrency.domain.data.repositories.cryptoRepo.CryptoRepo
import com.luisma.cryptocurrency.domain.data.repositories.cryptoRepo.ICryptoRepo
import com.luisma.cryptocurrency.dataServices.network.api.ICryptoApi
import com.luisma.cryptocurrency.dataServices.network.constants.NetworkConstants
import com.luisma.cryptocurrency.domain.data.mappers.CryptoDetailsMapper
import com.luisma.cryptocurrency.domain.data.utils.helpers.SimpleResponseHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    //External libs
    @Singleton
    @Provides
    fun cryptocurrencyNetwork(): ICryptoApi {
        return Retrofit
            .Builder()
            .baseUrl(NetworkConstants.Routes.baseRoute)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ICryptoApi::class.java)
    }

    //Domain
    @Provides
    fun cryptocurrencyRepo(
        cryptoApi: ICryptoApi,
        cryptoMapper: CryptoMapper,
        simpleResponseHandler: SimpleResponseHandler,
        cryptoDetailsMapper: CryptoDetailsMapper,
    ): ICryptoRepo {
        return CryptoRepo(
            cryptoApi = cryptoApi,
            cryptoMapper = cryptoMapper,
            simpleResponseHandler = simpleResponseHandler,
            cyptoDetailsMapper = cryptoDetailsMapper,
        )
    }

}