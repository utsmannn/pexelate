package com.utsman.collection.data.entity

import com.utsman.core.data.BaseEquatable

data class Collection(
    var id: String = "",
    var title: String = "",
    var photosCount: Int = 0,
    var cover: String = ""
) : BaseEquatable("$id$title")