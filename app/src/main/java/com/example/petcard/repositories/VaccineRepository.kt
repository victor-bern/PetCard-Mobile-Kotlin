package com.example.petcard.repositories

import com.example.petcard.listeners.ApiListerner
import com.example.petcard.models.Vaccine
import com.example.petcard.services.VaccineService
import com.example.petcard.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VaccineRepository {
    private val vaccineService = NetworkUtils.getRetrofitInstance("http://10.0.2.2:8080/").create(VaccineService::class.java)

    fun getVaccinesByPetId(id: Long, listener: ApiListerner<List<Vaccine>>) {
        val call = vaccineService.getVaccinesFromPet(id)
        call.enqueue(object : Callback<List<Vaccine>> {
            override fun onResponse(call: Call<List<Vaccine>>, response: Response<List<Vaccine>>) {
                listener.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<List<Vaccine>>, t: Throwable) {
                listener.onFailure(t.message.toString())
            }
        })
    }

}