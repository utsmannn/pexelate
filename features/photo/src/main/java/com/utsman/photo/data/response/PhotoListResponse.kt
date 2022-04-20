package com.utsman.photo.data.response


import com.google.gson.annotations.SerializedName

data class PhotoListResponse(
    @SerializedName("next_page")
    var nextPage: String? = null,
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("per_page")
    var perPage: Int? = null,
    @SerializedName("photos")
    var photos: List<PhotoResponse>? = null,
    @SerializedName("total_results")
    var totalResults: Int? = null
)