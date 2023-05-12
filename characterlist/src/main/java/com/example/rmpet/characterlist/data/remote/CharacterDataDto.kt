package com.example.rmpet.characterlist.data.remote

import kotlinx.serialization.Serializable

@Serializable
class CharacterDataDto(
    val info: InfoDto,
    val results: List<CharacterDto>
)
