package com.utsman.collection.data.response


import com.google.gson.annotations.SerializedName

data class CollectionListResponse(
    @SerializedName("collections")
    var collections: List<Collection>? = null,
    @SerializedName("next_page")
    var nextPage: String? = null,
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("per_page")
    var perPage: Int? = null,
    @SerializedName("total_results")
    var totalResults: Int? = null
) {
    data class Collection(
        @SerializedName("description")
        var description: String? = null,
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("media_count")
        var mediaCount: Int? = null,
        @SerializedName("photos_count")
        var photosCount: Int? = null,
        @SerializedName("private")
        var `private`: Boolean? = null,
        @SerializedName("title")
        var title: String? = null,
        @SerializedName("videos_count")
        var videosCount: Int? = null
    )
}