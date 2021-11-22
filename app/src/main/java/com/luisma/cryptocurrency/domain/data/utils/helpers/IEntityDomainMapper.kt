package com.luisma.cryptocurrency.domain.data.utils.helpers

interface IEntityDomainMapper<Entity,Domain > {
    fun mapEntityToDomain(entity: Entity): Domain
    fun mapDomainToEntity(domain: Domain): Entity
}