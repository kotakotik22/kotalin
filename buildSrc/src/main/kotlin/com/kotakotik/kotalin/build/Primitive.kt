package com.kotakotik.kotalin.build

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

@OptIn(ExperimentalSerializationApi::class)
internal val primitives = object {}.javaClass.getResourceAsStream("/primitives.json")!!.use {
    Json.decodeFromStream<Array<Primitive>>(it)
}

@Serializable
internal data class Primitive(
    val name: String,
    val zero: String,
    val customIterator: Boolean
)