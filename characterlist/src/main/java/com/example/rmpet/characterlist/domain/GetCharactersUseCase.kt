package com.example.rmpet.characterlist.domain

class GetCharactersUseCase(private val characterListRepository: CharacterListRepository) {

    suspend operator fun invoke(): List<CharacterData> {
        return characterListRepository.fetchCharacters()
    }
}
