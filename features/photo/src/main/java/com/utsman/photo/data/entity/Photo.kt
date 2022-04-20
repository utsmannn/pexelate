package com.utsman.photo.data.entity

import com.utsman.core.data.BaseEquatable

data class Photo(
    var alt: String = "",
    var avgColor: String = "",
    var id: Int = 0,
    var liked: Boolean = false,
    var photographer: String = "",
    var sources: Source = Source(),
    var url: String = ""
) : BaseEquatable("$id$url") {
    data class Source(
        var large: String = "",
        var medium: String = "",
        var original: String = "",
        var small: String = "",
        var tiny: String = ""
    )
}