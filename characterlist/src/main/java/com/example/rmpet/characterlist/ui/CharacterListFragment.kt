package com.example.rmpet.characterlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rmpet.characterlist.data.remote.CharacterListEndpoint
import com.example.rmpet.characterlist.databinding.FragmentCharacterListBinding
import com.example.rmpet.characterlist.di.CharacterListComponentProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class CharacterListFragment : Fragment() {

    @Inject
    lateinit var retrofit: Retrofit
    private var _binding: FragmentCharacterListBinding? = null
    private val binding: FragmentCharacterListBinding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireContext().applicationContext as CharacterListComponentProvider)
            .provideCharacterListComponent()
            .inject(this)

        val characterAdapter = CharactersAdapter()
        binding.charactersList.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(context)
        }
        val service = retrofit.create(CharacterListEndpoint::class.java)
        lifecycleScope.launch {
            val characters = withContext(Dispatchers.IO) {
                service.loadCharacters().results.map { dto ->
                    CharacterModel(
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
            }
            characterAdapter.setCharacters(characters)
        }
    }
}
