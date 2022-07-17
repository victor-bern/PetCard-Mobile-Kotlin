package com.example.petcard.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petcard.databinding.RowPetsListBinding
import com.example.petcard.listeners.ItemPetListener
import com.example.petcard.models.Pet
import com.example.petcard.views.viewholders.ListPetsViewHolder

class ListPetAdapter(private val listener: ItemPetListener) : RecyclerView.Adapter<ListPetsViewHolder>() {
    private var pets = listOf<Pet>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPetsViewHolder {
        val binding = RowPetsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListPetsViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ListPetsViewHolder, position: Int) {
        holder.bind(pets[position])
    }

    override fun getItemCount(): Int {
        return pets.count()
    }

    fun updatePets(pets: List<Pet>) {
        this.pets = pets
        notifyDataSetChanged()
    }
}