package com.example.petcard.views.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.petcard.R
import com.example.petcard.databinding.RowPetsListBinding
import com.example.petcard.models.AnimalType
import com.example.petcard.models.Pet

class ListPetsViewHolder(val binding: RowPetsListBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(pet: Pet) {
        binding.textPetName.text = pet.name
        if (pet.animalType == AnimalType.DOG) {
            binding.imagePetIcon.setImageResource(R.drawable.dog_icon)
        } else {
            binding.imagePetIcon.setImageResource(R.drawable.cat_icon)
        }
    }
}