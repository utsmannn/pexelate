package com.utsman.core.data

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import com.utsman.core.QueryMapData

abstract class Decodable {

    // auto convert from field to map
    fun toMap(ignoreNamingPolicy: Boolean = false): QueryMapData {
        val namingPolicy = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES

        val declareField = this::class.java.declaredFields.associate {
            it.isAccessible = true
            val name = if (ignoreNamingPolicy) it.name else namingPolicy.translateName(it)
            val value = it.get(this)
            val serializedName = it.getAnnotation(SerializedName::class.java)
            val serialNameValue = serializedName?.value ?: name
            serialNameValue to value
        }.filter { it.value != null } as QueryMapData

        return declareField
    }

    fun asString(): String {
        return toMap().toString()
    }

    fun asJsonString(): String {
        val gson = GsonBuilder()
            .setPrettyPrinting()
            .create()
        return gson.toJson(this)
    }

    fun validator(): Pair<Boolean, String> {
        val declareAccount = this::class.java.declaredFields.map {
            it.isAccessible = true
            Pair(it.name, it.get(this))
        }.firstOrNull { it.second == "" || it.second == 0 }

        val hasNext = declareAccount?.second == null
        return Pair(hasNext, declareAccount?.first.orEmpty())
    }
}