package com.example.petcard.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.petcard.dtos.LoginRequestDto
import com.example.petcard.models.User
import com.example.petcard.services.LoginService
import com.example.petcard.utils.NetworkUtils
import com.example.petcard.utils.PetsPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivityVIewModel(context: Application) : AndroidViewModel(context) {
    private val loginService = NetworkUtils.getRetrofitInstance("http://10.0.2.2:8080/").create(LoginService::class.java)
    private val petsPreference = PetsPreference(context)
    private var _isLogged = MutableLiveData<Boolean>()
    val isLogged: LiveData<Boolean> = _isLogged


    fun doLogin(email: String, password: String) {
        val call: Call<User> = loginService.login(LoginRequestDto(email, password))
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.code() != 200) {
                    _isLogged.value = false
                    return
                }

                petsPreference.store("userId", response.body()!!.id.toString())
                petsPreference.store("userEmail", response.body()!!.email)

                _isLogged.value = true
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                _isLogged.value = false
            }

        })
    }
}