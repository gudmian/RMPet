package com.example.rmpet.characterlist.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rmpet.characterlist.data.remote.CharacterDataDto
import com.example.rmpet.characterlist.data.remote.CharacterListEndpoint
import com.example.rmpet.characterlist.databinding.FragmentCharacterListBinding
import com.example.rmpet.characterlist.di.CharacterListComponentProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
        retrofit.create(CharacterListEndpoint::class.java).loadCharacters()
            .enqueue(object : Callback<CharacterDataDto> {
                override fun onResponse(call: Call<CharacterDataDto>, response: Response<CharacterDataDto>) {
                    response.body()?.results?.map { dto ->
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
                    }?.let(characterAdapter::setCharacters)
                }

                override fun onFailure(call: Call<CharacterDataDto>, t: Throwable) {
                    Log.e("CHRES", "CHRES onFailure")
                }
            })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = CharacterListFragment()
    }
}
