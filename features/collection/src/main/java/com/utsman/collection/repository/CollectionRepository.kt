package com.utsman.collection.repository

import com.utsman.collection.data.entity.Collection
import com.utsman.core.state.StateEvent
import kotlinx.coroutines.flow.StateFlow

interface CollectionRepository {
    val collectionListHome: StateFlow<StateEvent<List<Collection>>>

    suspend fun getCollectionListHome()
}