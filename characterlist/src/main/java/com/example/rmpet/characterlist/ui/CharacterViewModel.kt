package com.example.rmpet.characterlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmpet.characterlist.domain.CharacterData
import com.example.rmpet.characterlist.domain.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _characterFlow: MutableStateFlow<List<CharacterData>> = MutableStateFlow(emptyList())
    val characterFlow: StateFlow<List<CharacterData>> = _characterFlow

    init {
        viewModelScope.launch {
            _characterFlow.value = getCharactersUseCase()
        }
    }
}
