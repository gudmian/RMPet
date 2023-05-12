package com.example.rmpet.characterlist.di

import com.example.rmpet.characterlist.data.CharacterListRepositoryImpl
import com.example.rmpet.characterlist.data.remote.CharacterListEndpoint
import com.example.rmpet.characterlist.domain.CharacterListRepository
import com.example.rmpet.characterlist.domain.GetCharactersUseCase
import com.example.rmpet.characterlist.ui.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val characterListModule = module {
    single<CharacterListEndpoint> {
        get<Retrofit>().create(CharacterListEndpoint::class.java)
    }

    single<CharacterListRepository> { CharacterListRepositoryImpl(get()) }

    single { GetCharactersUseCase(get()) }

    viewModel { CharacterViewModel(get()) }
}
