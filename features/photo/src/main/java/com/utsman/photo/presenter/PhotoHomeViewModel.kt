package com.utsman.photo.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.utsman.photo.repository.PhotoRepository
import kotlinx.coroutines.launch

class PhotoHomeViewModel(
    private val repository: PhotoRepository
) : ViewModel() {
    val photoListEventLiveData = repository.photoListState.asLiveData(viewModelScope.coroutineContext)

    fun getListPhoto() = viewModelScope.launch {
        repository.getPhotoList()
    }
}