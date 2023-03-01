package com.example.rmpet.characterlist.ui

class CharacterModel(
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val gender: CharacterGender,
    val image: String
)

enum class CharacterStatus {
    ALIVE, DEAD, UNKNOWN
}

enum class CharacterGender {
    MALE, FEMALE, GENDERLESS, UNKNOWN
}
