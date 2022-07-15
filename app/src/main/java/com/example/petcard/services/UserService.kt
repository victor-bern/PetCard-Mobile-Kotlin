package com.example.petcard.services

import com.example.petcard.dtos.UserCreateDto
import com.example.petcard.models.Pet
import com.example.petcard.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @POST("api/v1/users")
    fun createUser(@Body user: UserCreateDto): Call<User>

    @GET("api/v1/users/{id}/pets")
    fun getPetsFromUser(@Path("id") id: Long): Call<List<Pet>>
}