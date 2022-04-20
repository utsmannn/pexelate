package com.utsman.photo.sources

import com.utsman.core.extensions.fetchState
import com.utsman.core.extensions.map
import com.utsman.core.state.StateEvent
import com.utsman.photo.data.Mapper
import com.utsman.photo.data.entity.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PhotoDataSources(private val webServices: PhotoWebServices) {

    suspend fun getPhotoList(): Flow<StateEvent<List<Photo>>> {
        return webServices.getList().fetchState()
            .map { it.map { Mapper.mapPhotoListResponseToEntity(this) } }
    }

    suspend fun getPhoto(photoId: String): Flow<StateEvent<Photo>> {
        return webServices.getPhoto(photoId).fetchState()
            .map { it.map { Mapper.mapPhotoResponseToEntity(this) } }
    }
}