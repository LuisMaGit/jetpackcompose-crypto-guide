package com.luisma.cryptocurrency.dataServices.internal.source

import com.luisma.cryptocurrency.CryptoDatabase
import cryptodb.CryptoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptoLocal(
    db: CryptoDatabase,
) : ICryptoLocal {

    private val queries = db.cryptoEntityQueries


    override suspend fun getCryptos(): List<CryptoEntity> {
        return withContext(Dispatchers.IO) {
            queries.getCryptos().executeAsList()
        }
    }

    override suspend fun insertCryptos(entities: List<CryptoEntity>): List<CryptoEntity> {

        withContext(Dispatchers.IO) {
            entities.forEach {
                queries.deleteCryptoById(it.id)
                queries.insertCrypto(it)
            }
        }

        return queries.getCryptos().executeAsList()
    }

    override suspend fun getLastUpdateCryptos(): String {
        return withContext(Dispatchers.IO) {
            queries.getCryptoLastUpdate().executeAsOneOrNull() ?: ""
        }
    }

    override suspend fun insertLastUpdateCrypto(date: String): String {
        withContext(Dispatchers.IO) {
            queries.insertLastUpdate(date)
        }

        return getLastUpdateCryptos()
    }
}