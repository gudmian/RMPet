package com.example.rmpet.characterlist.data

import com.example.rmpet.characterlist.data.remote.CharacterListEndpoint
import com.example.rmpet.characterlist.domain.CharacterData
import com.example.rmpet.characterlist.domain.CharacterGender
import com.example.rmpet.characterlist.domain.CharacterListRepository
import com.example.rmpet.characterlist.domain.CharacterStatus

class CharacterListRepositoryImpl(
    private val characterListEndpoint: CharacterListEndpoint
) : CharacterListRepository {

    override suspend fun fetchCharacters(): List<CharacterData> {
        val characters = characterListEndpoint.loadCharacters().results.map { dto ->
            CharacterData(
                name = dto.name,
                status = when (dto.status) {
                    "Alive" -> CharacterStatus.ALIVE
                    "Dead" -> CharacterStatus.DEAD
                    else -> CharacterStatus.UNKNOWN
                },
                species = dto.species,
                gender = when (dto.gender) {
                    "Male" -> CharacterGender.MALE
                    "Female" -> CharacterGender.FEMALE
                    "Genderless" -> CharacterGender.GENDERLESS
                    else -> CharacterGender.UNKNOWN
                },
                image = dto.image
            )
        }
        return characters
    }
}
