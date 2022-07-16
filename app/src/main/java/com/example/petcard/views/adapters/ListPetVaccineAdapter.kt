package com.example.petcard.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petcard.databinding.ListPetsVaccineRowItemBinding
import com.example.petcard.listeners.ItemVaccineListener
import com.example.petcard.models.Vaccine
import com.example.petcard.views.viewholders.ListPetVaccinesViewHolder

class ListPetVaccineAdapter(val listener: ItemVaccineListener) : RecyclerView.Adapter<ListPetVaccinesViewHolder>() {
    private var vaccines: List<Vaccine> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPetVaccinesViewHolder {
        val binding = ListPetsVaccineRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListPetVaccinesViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ListPetVaccinesViewHolder, position: Int) {
        holder.bind(vaccines[position])
    }

    override fun getItemCount(): Int {
        return vaccines.size
    }

    fun updateList(vaccines: List<Vaccine>) {
        this.vaccines = vaccines
        notifyDataSetChanged()
    }
}