package com.example.petcard.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.petcard.databinding.ActivityListVaccinesPetBinding
import com.example.petcard.listeners.ItemVaccineListener
import com.example.petcard.viewmodels.ListVaccinesPetsViewModel
import com.example.petcard.views.adapters.ListPetVaccineAdapter

class ListVaccinesPetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListVaccinesPetBinding
    private lateinit var viewModel: ListVaccinesPetsViewModel
    private lateinit var adapter: ListPetVaccineAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListVaccinesPetBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ListVaccinesPetsViewModel::class.java]
        adapter = ListPetVaccineAdapter(object : ItemVaccineListener {
            override fun onClickButton(id: Long) {
                TODO("Not yet implemented")
            }

        })

        setContentView(binding.root)
    }
}