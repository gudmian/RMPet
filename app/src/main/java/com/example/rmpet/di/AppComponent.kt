package com.example.rmpet.di

import android.content.Context
import com.example.rmpet.characterlist.di.CharacterListComponent
import com.example.rmpet.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppConfigurationModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    fun plusCharacterListComponent(): CharacterListComponent

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent

    }
}
