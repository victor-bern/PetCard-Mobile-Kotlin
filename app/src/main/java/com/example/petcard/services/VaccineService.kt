package com.example.petcard.services

import com.example.petcard.models.Vaccine
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface VaccineService {
    @GET("api/v1/vaccines/from-pet/{petId}")
    fun getVaccinesFromPet(@Path("petId") petId: Long): Call<List<Vaccine>>

    @GET("api/v1/vaccines/{petId}")
    fun getVaccine(id: Long): Call<Vaccine?>
}