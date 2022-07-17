package com.example.petcard.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.petcard.listeners.ApiListerner
import com.example.petcard.models.Vaccine
import com.example.petcard.repositories.VaccineRepository

class ListVaccinesPetsViewModel(application: Application) : AndroidViewModel(application) {
    private var _vaccines = MutableLiveData<List<Vaccine>>()
    val vaccines: LiveData<List<Vaccine>> = _vaccines

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val vaccineRepository = VaccineRepository()

    fun getVaccines(petId: Long) {
        vaccineRepository.getVaccinesByPetId(petId, object : ApiListerner<List<Vaccine>> {
            override fun onSuccess(data: List<Vaccine>) {
                _vaccines.value = data
            }

            override fun onFailure(message: String) {
                _error.value = message
            }
        })
    }
}