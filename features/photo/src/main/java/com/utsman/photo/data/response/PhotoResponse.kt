package com.utsman.photo.data.response


import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("alt")
    var alt: String? = null,
    @SerializedName("avg_color")
    var avgColor: String? = null,
    @SerializedName("height")
    var height: Int? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("liked")
    var liked: Boolean? = null,
    @SerializedName("photographer")
    var photographer: String? = null,
    @SerializedName("photographer_id")
    var photographerId: Int? = null,
    @SerializedName("photographer_url")
    var photographerUrl: String? = null,
    @SerializedName("src")
    var src: Src? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("width")
    var width: Int? = null
) {

    data class Src(
        @SerializedName("landscape")
        var landscape: String? = null,
        @SerializedName("large")
        var large: String? = null,
        @SerializedName("large2x")
        var large2x: String? = null,
        @SerializedName("medium")
        var medium: String? = null,
        @SerializedName("original")
        var original: String? = null,
        @SerializedName("portrait")
        var portrait: String? = null,
        @SerializedName("small")
        var small: String? = null,
        @SerializedName("tiny")
        var tiny: String? = null
    )
}