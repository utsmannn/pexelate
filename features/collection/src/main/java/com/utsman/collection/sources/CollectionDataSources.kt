package com.utsman.collection.sources

import com.utsman.collection.data.Mapper
import com.utsman.collection.data.entity.Collection
import com.utsman.core.extensions.fetchState
import com.utsman.core.extensions.map
import com.utsman.core.state.StateEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CollectionDataSources(
    private val webServices: CollectionWebServices
) {

    suspend fun getListCollection(): Flow<StateEvent<List<Collection>>> {
        val data = webServices.getList().fetchState()
            .map {
                it.map {
                    Mapper.mapCollectionResponseToEntity(this).map { col ->
                        val mediaResponse = webServices.getMedia(col.id)
                        if (mediaResponse.isSuccessful) {
                            val firstMedia = mediaResponse.body()?.media?.firstOrNull()
                            if (firstMedia != null) {
                                val media = Mapper.mapMediaResponseToEntity(firstMedia)
                                val cover = media.mediumUrl
                                col.cover = cover
                            }
                        }

                        col
                    }
                }
            }

        return data
    }
}