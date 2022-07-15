package com.example.petcard.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.petcard.R
import com.example.petcard.dtos.RegisterPetDto
import com.example.petcard.listeners.ApiListerner
import com.example.petcard.models.AnimalType
import com.example.petcard.models.Pet
import com.example.petcard.repositories.PetRepository
import com.example.petcard.utils.PetsPreference

class RegisterPetViewModel(val context: Application) : AndroidViewModel(context) {
    private val petRepository = PetRepository(context)
    private val preferences = PetsPreference(context)

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private var _pet = MutableLiveData<Pet>()
    val pet: LiveData<Pet> = _pet


    fun addPet(name: String, race: String, type: AnimalType) {
        val userId = preferences.getValue("userId")
        if (userId == "") {
            _error.value = context.getString(R.string.error_pet_register)
            return
        }
        val pet = RegisterPetDto(name = name, race = race, userId = userId.toLong(), type)
        petRepository.addPet(pet, object : ApiListerner<Pet> {
            override fun onSuccess(data: Pet) {
                _pet.value = data
            }

            override fun onFailure(message: String) {
                _error.value = message
            }
        })
    }


}