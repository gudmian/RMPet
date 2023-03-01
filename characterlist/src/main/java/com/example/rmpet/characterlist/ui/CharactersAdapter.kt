package com.example.rmpet.characterlist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rmpet.characterlist.R
import com.example.rmpet.characterlist.databinding.ListItemCharacterBinding

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.Holder>() {

    private var data: List<CharacterModel> = listOf()

    fun setCharacters(newData: List<CharacterModel>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        ListItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class Holder(private val binding: ListItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: CharacterModel) {
            binding.listItemCharacterName.text = model.name
            binding.listItemCharacterSpecies.text = model.species
            binding.listItemCharacterGender.setImageResource(getGenderImage(model.gender))
            binding.listItemCharacterStatus.setImageResource(getCharacterStatus(model.status))
        }

        private fun getGenderImage(gender: CharacterGender): Int = when (gender) {
            CharacterGender.MALE -> R.drawable.ic_gender_male
            CharacterGender.FEMALE -> R.drawable.ic_gender_female
            CharacterGender.GENDERLESS -> R.drawable.ic_gender_genderless
            CharacterGender.UNKNOWN -> R.drawable.ic_unknown
        }

        private fun getCharacterStatus(status: CharacterStatus): Int = when(status) {
            CharacterStatus.ALIVE -> R.drawable.ic_status_alive
            CharacterStatus.DEAD -> R.drawable.ic_status_dead
            CharacterStatus.UNKNOWN -> R.drawable.ic_unknown
        }
    }
}
