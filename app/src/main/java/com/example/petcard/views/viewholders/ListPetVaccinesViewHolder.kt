package com.example.petcard.views.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.petcard.databinding.ListPetsVaccineRowItemBinding
import com.example.petcard.listeners.ItemVaccineListener
import com.example.petcard.models.Vaccine
import java.text.SimpleDateFormat
import java.util.*

class ListPetVaccinesViewHolder(private val binding: ListPetsVaccineRowItemBinding, listener: ItemVaccineListener) : RecyclerView.ViewHolder(binding.root) {
    private val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    fun bind(vaccine: Vaccine) {
        binding.textVaccineName.text = vaccine.name
        binding.textFirstVaccine.text = simpleDateFormat.format(vaccine.firstVaccine)
        binding.textNextVaccine.text = simpleDateFormat.format(vaccine.nextVaccine)
    }
}