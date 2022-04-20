package com.utsman.core.data

import com.utsman.core.extensions.longId

open class BaseEquatable(private val identifier: String): Equatable, Decodable() {
    override val uniqueId: String = identifier

    override val longId: Long get() = identifier.longId()

    override fun isEqual(identifier: String): Boolean {
        return identifier == this.identifier
    }
}