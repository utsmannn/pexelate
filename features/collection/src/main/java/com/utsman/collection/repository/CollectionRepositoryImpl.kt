package com.utsman.collection.repository

import com.utsman.collection.data.entity.Collection
import com.utsman.collection.sources.CollectionDataSources
import com.utsman.core.extensions.loadingStateFlowEvent
import com.utsman.core.state.StateEvent
import kotlinx.coroutines.flow.StateFlow

class CollectionRepositoryImpl(
    private val dataSources: CollectionDataSources
) : CollectionRepository {

    private val _collectionListHome = loadingStateFlowEvent<List<Collection>>()
    override val collectionListHome: StateFlow<StateEvent<List<Collection>>>
        get() = _collectionListHome

    override suspend fun getCollectionListHome() {
        dataSources.getListCollection().collect {
            _collectionListHome.emit(it)
        }
    }
}