package com.example.petcard.repositories

import android.content.Context
import com.example.petcard.R
import com.example.petcard.dtos.UserCreateDto
import com.example.petcard.listeners.ApiListerner
import com.example.petcard.models.Pet
import com.example.petcard.models.User
import com.example.petcard.services.UserService
import com.example.petcard.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val context: Context) {
    private val userService = NetworkUtils.getRetrofitInstance("http://10.0.2.2:8080/").create(UserService::class.java)

    fun createUser(user: UserCreateDto, listener: ApiListerner<User>) {
        val call: Call<User> = userService.createUser(user)
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.code() != 200) {
                    listener.onFailure(context.getString(R.string.error_user_signup))
                }
                listener.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                listener.onFailure(t.message.toString())
            }
        })

    }

    fun getPetsFromUser(id: Long, listener: ApiListerner<List<Pet>>) {
        val call: Call<List<Pet>> = userService.getPetsFromUser(id)
        call.enqueue(object : Callback<List<Pet>> {
            override fun onResponse(call: Call<List<Pet>>, response: Response<List<Pet>>) {
                if (response.code() != 200) {
                    listener.onFailure("Houve um erro ao recuperar seus pets")
                }

                listener.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<List<Pet>>, t: Throwable) {
                listener.onFailure(t.message.toString())
            }

        })
    }
}