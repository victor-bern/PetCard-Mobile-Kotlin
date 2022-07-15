package com.example.petcard.dtos

import com.example.petcard.models.AnimalType

data class RegisterPetDto(val name: String, val race: String, val userId: Long, val animalType: AnimalType)
