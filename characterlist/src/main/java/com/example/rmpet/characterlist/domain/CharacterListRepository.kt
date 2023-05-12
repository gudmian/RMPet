package com.example.rmpet.characterlist.domain

interface CharacterListRepository {

    suspend fun fetchCharacters(): List<CharacterData>

}
