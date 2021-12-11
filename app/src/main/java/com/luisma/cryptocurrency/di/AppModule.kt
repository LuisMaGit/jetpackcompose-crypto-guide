package com.luisma.cryptocurrency.di

import android.app.Application
import android.content.Context
import com.luisma.cryptocurrency.CryptoDatabase
import com.luisma.cryptocurrency.dataServices.internal.source.CryptoLocal
import com.luisma.cryptocurrency.dataServices.internal.source.ICryptoLocal
import com.luisma.cryptocurrency.domain.data.mappers.CryptoMapper
import com.luisma.cryptocurrency.domain.data.repositories.cryptoRepo.CryptoRepo
import com.luisma.cryptocurrency.domain.data.repositories.cryptoRepo.ICryptoRepo
import com.luisma.cryptocurrency.dataServices.network.api.ICryptoApi
import com.luisma.cryptocurrency.dataServices.network.constants.NetworkConstants
import com.luisma.cryptocurrency.domain.app.repositories.PreferencesRepo
import com.luisma.cryptocurrency.domain.data.mappers.CryptoDetailsMapper
import com.luisma.cryptocurrency.domain.data.utils.helpers.SimpleResponseHandler
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

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

    @Singleton
    @Provides
    fun preferencesRepo(
        @ApplicationContext appContext: Context
    ): PreferencesRepo{
        return PreferencesRepo(appContext)
    }

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver{
        return AndroidSqliteDriver(
            schema = CryptoDatabase.Schema,
            context = app,
            name = "Crypto.db"
        )
    }

    @Provides
    @Singleton
    fun cryptocurrencyInternal(driver: SqlDriver): ICryptoLocal{
        return CryptoLocal(CryptoDatabase(driver))
    }


    //Domain
    @Provides
    fun cryptocurrencyRepo(
        cryptoApi: ICryptoApi,
        cryptoLocal: ICryptoLocal,
        cryptoMapper: CryptoMapper,
        simpleResponseHandler: SimpleResponseHandler,
        cryptoDetailsMapper: CryptoDetailsMapper,
    ): ICryptoRepo {
        return CryptoRepo(
            cryptoApi = cryptoApi,
            cryptoLocal = cryptoLocal,
            cryptoMapper = cryptoMapper,
            simpleResponseHandler = simpleResponseHandler,
            cryptoDetailsMapper = cryptoDetailsMapper,
        )
    }


}