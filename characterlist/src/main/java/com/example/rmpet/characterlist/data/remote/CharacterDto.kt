package com.example.rmpet.characterlist.data.remote

import kotlinx.serialization.Serializable

@Serializable
class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationInfoDto,
    val location: LocationInfoDto,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
)
