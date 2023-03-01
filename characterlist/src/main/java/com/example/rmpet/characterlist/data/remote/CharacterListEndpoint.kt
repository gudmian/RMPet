package com.example.rmpet.characterlist.data.remote

import retrofit2.Call
import retrofit2.http.GET

interface CharacterListEndpoint {

    @GET("api/character")
    fun loadCharacters(): Call<CharacterDataDto>
}
