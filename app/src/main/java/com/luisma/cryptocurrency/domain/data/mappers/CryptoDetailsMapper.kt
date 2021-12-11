package com.luisma.cryptocurrency.domain.data.mappers

import com.luisma.cryptocurrency.dataServices.network.dtos.cryptoDtos.cryptoDetailsDto.CryptoDetailsDto
import com.luisma.cryptocurrency.domain.data.models.cryptoModels.cryptoDetails.CryptoDetails
import com.luisma.cryptocurrency.domain.data.models.cryptoModels.cryptoDetails.Tag
import com.luisma.cryptocurrency.domain.data.models.cryptoModels.cryptoDetails.Team
import javax.inject.Inject

class CryptoDetailsMapper @Inject constructor() {
    fun mapSourceToDomain(dto: CryptoDetailsDto): CryptoDetails {
        return CryptoDetails(
            id = dto.id,
            isNew = dto.isNew,
            isActive = dto.isActive,
            rank = dto.rank,
            name = dto.name,
            description = dto.description,
            symbol = dto.symbol,
            tags = dto.tags.map { Tag(
                id = it.id,
                name = it.name
            ) },
            team = dto.team.map { Team(
                id = it.id,
                name = it.name,
                position = it.position
            ) }
        )
    }
}