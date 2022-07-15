package com.example.petcard.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.petcard.databinding.ActivityListVaccinesPetBinding
import com.example.petcard.viewmodels.ListVaccinesPetsViewModel

class ListVaccinesPetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListVaccinesPetBinding
    private lateinit var viewModel: ListVaccinesPetsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListVaccinesPetBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ListVaccinesPetsViewModel::class.java]

        setContentView(binding.root)
    }
}