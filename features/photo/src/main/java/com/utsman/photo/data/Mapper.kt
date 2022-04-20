package com.utsman.photo.data

import com.utsman.core.extensions.orFalse
import com.utsman.core.extensions.orNol
import com.utsman.photo.data.entity.Photo
import com.utsman.photo.data.response.PhotoListResponse
import com.utsman.photo.data.response.PhotoResponse

object Mapper {

    fun mapPhotoResponseToEntity(response: PhotoResponse?): Photo {
        val sourcesResponse = response?.src
        val sources = Photo.Source(
            large = sourcesResponse?.large.orEmpty(),
            medium = sourcesResponse?.medium.orEmpty(),
            original = sourcesResponse?.original.orEmpty(),
            small = sourcesResponse?.small.orEmpty(),
            tiny = sourcesResponse?.tiny.orEmpty()
        )
        return Photo(
            alt = response?.alt.orEmpty(),
            avgColor = response?.avgColor.orEmpty(),
            id = response?.id.orNol(),
            liked = response?.liked.orFalse(),
            photographer = response?.photographer.orEmpty(),
            sources = sources,
            url = response?.url.orEmpty()
        )
    }

    fun mapPhotoListResponseToEntity(response: PhotoListResponse?): List<Photo> {
        return response?.photos?.map { mapPhotoResponseToEntity(it) }.orEmpty()
    }
}