package com.example.petcard.services

import com.example.petcard.dtos.LoginRequestDto
import com.example.petcard.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("api/auth")
    fun login(@Body login: LoginRequestDto): Call<User>
}