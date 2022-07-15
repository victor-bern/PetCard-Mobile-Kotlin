package com.example.petcard.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.petcard.listeners.ApiListerner
import com.example.petcard.models.Pet
import com.example.petcard.repositories.UserRepository
import com.example.petcard.utils.PetsPreference

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private var _pets = MutableLiveData<List<Pet>>()
    val pets: LiveData<List<Pet>> = _pets
    private var _errors = MutableLiveData<String>()
    val errors: LiveData<String> = _errors

    private val userRepository = UserRepository(application)
    private val sharedPreferences = PetsPreference(application)


    fun getPets() {
        val id = sharedPreferences.getValue("userId")

        if (id.isEmpty()) {
            _errors.value = "Houve um erro ao recuperar os dados, faça login novamente"
        }

        userRepository.getPetsFromUser(id.toLong(), object : ApiListerner<List<Pet>> {
            override fun onSuccess(data: List<Pet>) {
                _pets.value = data
            }

            override fun onFailure(message: String) {
                _errors.value = message
            }

        })
    }

}