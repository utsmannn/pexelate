package com.utsman.photo.repository

import com.utsman.core.extensions.loadingStateFlowEvent
import com.utsman.core.state.StateEvent
import com.utsman.photo.data.entity.Photo
import com.utsman.photo.sources.PhotoDataSources
import kotlinx.coroutines.flow.StateFlow

class PhotoRepositoryImpl(
    private val dataSources: PhotoDataSources
) : PhotoRepository {

    private val _photoListState = loadingStateFlowEvent<List<Photo>>()
    override val photoListState: StateFlow<StateEvent<List<Photo>>>
        get() = _photoListState

    private val _photoState = loadingStateFlowEvent<Photo>()
    override val photoState: StateFlow<StateEvent<Photo>>
        get() = _photoState

    override suspend fun getPhotoList() {
        dataSources.getPhotoList().collect {
            _photoListState.emit(it)
        }
    }

    override suspend fun getPhoto(photoId: String) {
        dataSources.getPhoto(photoId).collect {
            _photoState.emit(it)
        }
    }
}