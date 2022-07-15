package com.example.petcard.listeners

interface ApiListerner<T> {
    fun onSuccess(data: T)
    fun onFailure(message: String)
}