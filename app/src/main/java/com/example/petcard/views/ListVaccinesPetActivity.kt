package com.example.petcard.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.petcard.databinding.ActivityListVaccinesPetBinding
import com.example.petcard.listeners.ItemVaccineListener
import com.example.petcard.viewmodels.ListVaccinesPetsViewModel
import com.example.petcard.views.adapters.ListPetVaccineAdapter
import com.google.android.material.snackbar.Snackbar

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
                TODO()
            }
        })
        setContentView(binding.root)
        handleExtras()
        handleObservers()

    }

    private fun handleObservers() {
        viewModel.vaccines.observe(this) {
            adapter.updateList(it)
        }
        viewModel.error.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun handleExtras() {
        val id = intent.getLongExtra("id", 0)
        viewModel.getVaccines(id)
    }
}