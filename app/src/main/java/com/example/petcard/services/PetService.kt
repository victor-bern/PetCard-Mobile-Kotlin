package com.example.petcard.services

import com.example.petcard.dtos.RegisterPetDto
import com.example.petcard.models.Pet
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PetService {
    @POST("api/v1/pets")
    fun addPet(@Body pet: RegisterPetDto): Call<Pet>
}