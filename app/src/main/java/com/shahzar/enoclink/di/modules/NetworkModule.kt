package com.shahzar.enoclink.di.modules

import com.google.gson.GsonBuilder
import com.shahzar.enoclink.data.repository.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    val BASE_URL = "https://jsonplaceholder.typicode.com/"


    @Provides
    fun provideApiService(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): ApiService {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build().create(ApiService::class.java)

    }

    @Provides
    fun provideOkHttp(): OkHttpClient {

        val okHttpBuilder = OkHttpClient.Builder()
        return okHttpBuilder.build()
    }


    @Provides
    fun provideGson(): GsonConverterFactory = GsonConverterFactory.create(GsonBuilder().create())

}