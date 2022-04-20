package com.utsman.collection.data

import com.utsman.collection.data.entity.Collection
import com.utsman.collection.data.entity.Media
import com.utsman.collection.data.response.CollectionListResponse
import com.utsman.collection.data.response.CollectionMediaResponse
import com.utsman.core.extensions.orNol

object Mapper {

    fun mapCollectionResponseToEntity(listResponse: CollectionListResponse?): List<Collection> {
        return listResponse?.collections?.map {
            Collection(
                id = it.id.orEmpty(),
                title = it.title.orEmpty(),
                photosCount = it.photosCount.orNol()
            )
        }.orEmpty()
    }

    fun mapMediaResponseToEntity(mediaResponse: CollectionMediaResponse.Media?): Media {
        return Media(
            id = mediaResponse?.id.orNol(),
            mediumUrl = mediaResponse?.src?.medium.orEmpty()
        )
    }
}