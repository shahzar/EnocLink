package com.shahzar.enoclink.data.repository

import com.shahzar.enoclink.data.model.SampleDataModel
import retrofit2.http.GET

interface ApiService {

    @GET("todos/1")
    suspend fun getSampleData(): SampleDataModel



}