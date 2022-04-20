package com.utsman.collection.sources

import com.utsman.collection.data.response.CollectionListResponse
import com.utsman.collection.data.response.CollectionMediaResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CollectionWebServices {

    @GET(EndPoint.COLLECTION)
    suspend fun getList(
        @Query(QueryKey.PER_PAGE) perPage: Int = 4
    ): Response<CollectionListResponse>

    @GET(EndPoint.COLLECTION_MEDIA)
    suspend fun getMedia(
        @Path(QueryKey.COLLECTION_ID) collectionId: String,
        @Query(QueryKey.PER_PAGE) perPage: Int = 10,
        @Query(QueryKey.TYPE) type: String = QueryKey.TYPE_PHOTO
    ): Response<CollectionMediaResponse>

    object EndPoint {
        const val COLLECTION = "collections/featured"
        const val COLLECTION_MEDIA = "collections/{${QueryKey.COLLECTION_ID}}"
    }

    object QueryKey {
        const val PER_PAGE = "per_page"
        const val COLLECTION_ID = "collection_id"
        const val TYPE = "type"
        const val TYPE_PHOTO = "photos"
    }
}