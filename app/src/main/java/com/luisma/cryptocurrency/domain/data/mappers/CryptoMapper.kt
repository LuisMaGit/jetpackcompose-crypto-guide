package com.luisma.cryptocurrency.domain.data.mappers

import com.luisma.cryptocurrency.dataServices.network.entities.cryptoEntities.CryptoEntity
import com.luisma.cryptocurrency.domain.data.utils.helpers.IEntityDomainMapper
import com.luisma.cryptocurrency.domain.data.models.cryptoModels.Crypto
import javax.inject.Inject


class CryptoMapper @Inject constructor() : IEntityDomainMapper<CryptoEntity, Crypto> {
    override fun mapEntityToDomain(entity: CryptoEntity): Crypto {
        return Crypto(
            id = entity.id,
            isActive = entity.isActive,
            isNew = entity.isNew,
            name = entity.name,
            symbol = entity.symbol,
            type = entity.type,
            rank = entity.rank,
        )
    }

    override fun mapDomainToEntity(domain: Crypto): CryptoEntity {
        TODO("Not yet implemented")
    }

    fun mapEntitiesToDomains(entities: List<CryptoEntity>): List<Crypto> {
        return entities.map { mapEntityToDomain(it) }
    }

}