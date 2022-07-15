package com.example.petcard.models

class User(var id: Long, var name: String, var email: String, var pets: List<Pet> = listOf())