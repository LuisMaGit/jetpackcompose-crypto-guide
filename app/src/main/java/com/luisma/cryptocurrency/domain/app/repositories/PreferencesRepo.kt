package com.luisma.cryptocurrency.domain.app.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

enum class DataStoreKey {
    Theme
}

private const val STORE_NAME = "CRYPTO_JETPACK_SETTINGS";

class PreferencesRepo(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = STORE_NAME)

    suspend fun save(key: DataStoreKey, value: String) {
        val dataStoreKey = stringPreferencesKey(key.toString())
        context.dataStore.edit {
            it[dataStoreKey] = value
        }
    }

    fun read(key: DataStoreKey): Flow<String?> {
        val dataStoreKey = stringPreferencesKey(key.toString())
        return context.dataStore.data.map {
            it[dataStoreKey]
        }
    }
}