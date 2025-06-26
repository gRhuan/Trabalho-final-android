package com.grhuan.cat.data.mapper

import com.grhuan.cat.data.model.CatEntity
import com.grhuan.cat.data.model.CatResponse

object CatMappers {
    fun toEntity(catResponse: CatResponse) = CatEntity(
        id = catResponse.id,
        url = catResponse.url,
        breeds = catResponse.breeds
    )

    fun toResponse(catEntity: CatEntity) = CatResponse(
        id = catEntity.id,
        url = catEntity.url,
        breeds = catEntity.breeds
    )
}