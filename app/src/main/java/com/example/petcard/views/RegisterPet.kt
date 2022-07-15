package com.example.petcard.views

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.petcard.R
import com.example.petcard.databinding.ActivityRegisterPetBinding
import com.example.petcard.models.AnimalType
import com.example.petcard.viewmodels.RegisterPetViewModel
import com.google.android.material.snackbar.Snackbar

class RegisterPet : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterPetBinding
    private lateinit var viewModel: RegisterPetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[RegisterPetViewModel::class.java]
        binding = ActivityRegisterPetBinding.inflate(layoutInflater)

        val items = resources.getStringArray(R.array.pet_type)
        val adapter = ArrayAdapter(this, R.layout.animal_item_menu, items)
        (binding.textFieldItemMenu as? AutoCompleteTextView)?.setAdapter(adapter)

        setContentView(binding.root)
        binding.buttonRegisterPet.setOnClickListener { handleButtonClick() }
        handleObservers()
    }


    private fun handleObservers() {
        viewModel.error.observe(this) {
            Snackbar.make(this, binding.root, it, Snackbar.LENGTH_SHORT).show()
        }

        viewModel.pet.observe(this) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun handleButtonClick() {
        val name = binding.editPetName.text.toString()
        if (name.isEmpty()) {
            binding.editPetName.error = getString(R.string.error_empty_field)
            return
        }
        val race = binding.editPetRace.text.toString()
        if (race.isEmpty()) {
            binding.editPetRace.error = getString(R.string.error_empty_field)
            return
        }
        if (binding.textFieldItemMenu.text.isEmpty()) {
            binding.menu.error = getString(R.string.animal_type_error_selection)
            return
        }
        val petType = if (binding.textFieldItemMenu.text.toString() == "\uD83D\uDC36") AnimalType.DOG else AnimalType.CAT
        viewModel.addPet(name, race, petType)
    }

}