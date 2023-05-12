package com.example.rmpet.characterlist.data.remote

import kotlinx.serialization.Serializable

@Serializable
class InfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
