package com.utsman.photo.sources

import com.utsman.photo.data.response.PhotoListResponse
import com.utsman.photo.data.response.PhotoResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoWebServices {

    @GET(EndPoint.LIST)
    suspend fun getList(
        @Query(QueryKey.PAGE) page: Int = 1,
        @Query(QueryKey.PER_PAGE) perPage: Int = 10
    ): Response<PhotoListResponse>

    @GET(EndPoint.PHOTO)
    suspend fun getPhoto(
        @Path(QueryKey.PHOTO_ID) photoId: String
    ): Response<PhotoResponse>

    object EndPoint {
        const val LIST = "curated"
        const val PHOTO = "photos/{${QueryKey.PHOTO_ID}}"
    }

    object QueryKey {
        const val PAGE = "page"
        const val PER_PAGE = "per_page"
        const val PHOTO_ID = "photo_id"
    }
}