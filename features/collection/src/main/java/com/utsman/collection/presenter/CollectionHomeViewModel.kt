package com.utsman.collection.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.utsman.collection.repository.CollectionRepository
import kotlinx.coroutines.launch

class CollectionHomeViewModel(
    private val repository: CollectionRepository
) : ViewModel() {
    val collectionHomeList =
        repository.collectionListHome.asLiveData(viewModelScope.coroutineContext)

    fun getCollectionHome() = viewModelScope.launch {
        repository.getCollectionListHome()
    }
}