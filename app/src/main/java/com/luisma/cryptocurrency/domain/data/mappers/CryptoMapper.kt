package com.luisma.cryptocurrency.domain.data.mappers

import com.luisma.cryptocurrency.dataServices.network.dtos.cryptoDtos.CryptoDto
import com.luisma.cryptocurrency.domain.data.models.cryptoModels.Crypto
import cryptodb.CryptoEntity
import javax.inject.Inject


class CryptoMapper @Inject constructor() {
    private fun mapDtoToEntity(dto: CryptoDto): CryptoEntity {
        return CryptoEntity(
            id = dto.id,
            isActive = if(dto.isActive) 1 else 0,
            isNew = if(dto.isNew) 1 else 0,
            name = dto.name,
            symbol = dto.symbol,
            type = dto.type,
            rank = dto.rank.toLong(),
        )
    }

    private fun mapEntityToDomain(entity: CryptoEntity): Crypto {
        return Crypto(
            id = entity.id,
            isActive = entity.isActive.toInt() == 1,
            isNew = entity.isActive.toInt() == 1,
            name = entity.name,
            symbol = entity.symbol,
            type = entity.type,
            rank = entity.rank.toInt(),
        )
    }



    fun mapDtosToEntities(dtos: List<CryptoDto>): List<CryptoEntity> {
        return dtos.map { mapDtoToEntity(it) }
    }

    fun mapEntitiesToDomain(entities: List<CryptoEntity>): List<Crypto>{
        return entities.map { mapEntityToDomain(it) }
    }

}

