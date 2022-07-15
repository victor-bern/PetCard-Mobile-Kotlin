package com.example.petcard.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.petcard.dtos.UserCreateDto
import com.example.petcard.listeners.ApiListerner
import com.example.petcard.models.User
import com.example.petcard.repositories.UserRepository
import com.example.petcard.utils.PetsPreference

class SignUpActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository = UserRepository(application)
    private val petsPreference = PetsPreference(application)

    private var _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _user

    private var _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error


    fun validatePasswords(password: String, passwordRepetead: String) = password == passwordRepetead

    fun createUser(name: String, email: String, password: String) {
        val user = UserCreateDto(name, email, password)

        userRepository.createUser(user, object : ApiListerner<User> {
            override fun onSuccess(data: User) {
                _user.value = data
                petsPreference.store("userId", data.id.toString())
                petsPreference.store("userEmail", data.email)
            }

            override fun onFailure(message: String) {
                _error.value = message
            }
        })

    }
}