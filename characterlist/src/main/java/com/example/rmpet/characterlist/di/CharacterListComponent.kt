package com.example.rmpet.characterlist.di

import com.example.rmpet.characterlist.ui.CharacterListFragment
import dagger.Subcomponent

@Subcomponent
interface CharacterListComponent {
    fun inject(characterListFragment: CharacterListFragment)
}
