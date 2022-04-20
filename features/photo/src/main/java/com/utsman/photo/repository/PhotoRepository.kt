package com.utsman.photo.repository

import com.utsman.core.state.StateEvent
import com.utsman.photo.data.entity.Photo
import kotlinx.coroutines.flow.StateFlow

interface PhotoRepository {
    val photoListState: StateFlow<StateEvent<List<Photo>>>
    val photoState: StateFlow<StateEvent<Photo>>

    suspend fun getPhotoList()
    suspend fun getPhoto(photoId: String)
}