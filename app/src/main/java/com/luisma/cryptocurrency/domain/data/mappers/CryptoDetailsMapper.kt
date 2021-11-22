package com.luisma.cryptocurrency.domain.data.mappers

import com.luisma.cryptocurrency.dataServices.network.entities.cryptoEntities.cryptoDetailsEntity.CryptoDetailsEntity
import com.luisma.cryptocurrency.domain.data.models.cryptoModels.cryptoDetails.CryptoDetails
import com.luisma.cryptocurrency.domain.data.models.cryptoModels.cryptoDetails.Tag
import com.luisma.cryptocurrency.domain.data.models.cryptoModels.cryptoDetails.Team
import com.luisma.cryptocurrency.domain.data.utils.helpers.IEntityDomainMapper
import javax.inject.Inject

class CryptoDetailsMapper @Inject constructor() :
    IEntityDomainMapper<CryptoDetailsEntity, CryptoDetails> {
    override fun mapEntityToDomain(entity: CryptoDetailsEntity): CryptoDetails {
        return CryptoDetails(
            id = entity.id,
            isNew = entity.isNew,
            isActive = entity.isActive,
            rank = entity.rank,
            name = entity.name,
            description = entity.description,
            symbol = entity.symbol,
            tags = entity.tags.map { Tag(
                id = it.id,
                name = it.name
            ) },
            team = entity.team.map { Team(
                id = it.id,
                name = it.name,
                position = it.position
            ) }
        )
    }

    override fun mapDomainToEntity(domain: CryptoDetails): CryptoDetailsEntity {
        TODO("Not yet implemented")
    }
}