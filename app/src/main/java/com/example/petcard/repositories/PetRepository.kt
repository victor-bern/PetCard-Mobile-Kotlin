package com.example.petcard.repositories

import android.content.Context
import com.example.petcard.R
import com.example.petcard.dtos.RegisterPetDto
import com.example.petcard.listeners.ApiListerner
import com.example.petcard.models.Pet
import com.example.petcard.services.PetService
import com.example.petcard.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PetRepository(val context: Context) {
    private val petService: PetService = NetworkUtils.getRetrofitInstance("http://10.0.2.2:8080/").create(PetService::class.java)

    fun addPet(pet: RegisterPetDto, listener: ApiListerner<Pet>) {
        val call: Call<Pet> = petService.addPet(pet)
        call.enqueue(object : Callback<Pet> {
            override fun onResponse(call: Call<Pet>, response: Response<Pet>) {
                if (response.code() != 200) {
                    listener.onFailure(context.getString(R.string.error_pet_register))
                }

                listener.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<Pet>, t: Throwable) {
                listener.onFailure(t.message.toString())
            }

        })
    }
}