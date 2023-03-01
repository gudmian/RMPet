package com.example.rmpet

import android.app.Application
import com.example.rmpet.characterlist.di.CharacterListComponent
import com.example.rmpet.characterlist.di.CharacterListComponentProvider
import com.example.rmpet.di.DaggerAppComponent

class App : Application(), CharacterListComponentProvider {
    private val appComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun provideCharacterListComponent(): CharacterListComponent = appComponent.plusCharacterListComponent()
}
