package com.utsman.core.data

interface Equatable {
    val uniqueId: String
    val longId: Long
    fun isEqual(identifier: String): Boolean
}