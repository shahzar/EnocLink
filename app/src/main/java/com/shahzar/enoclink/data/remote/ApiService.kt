package com.shahzar.enoclink.data.remote

import com.shahzar.enoclink.data.model.request.AvatarRequest
import com.shahzar.enoclink.data.model.request.LoginRequest
import com.shahzar.enoclink.data.model.response.AvatarResponse
import com.shahzar.enoclink.data.model.response.LoginResponse
import com.shahzar.enoclink.data.model.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("sessions/new")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @GET("users/{userId}")
    suspend fun getUserDetails(@Path("userId") userId: String): UserResponse

    @POST("users/{userId}/avatar")
    suspend fun uploadUserAvatar(@Path("userId") userId: String, @Body avatarRequest: AvatarRequest): AvatarResponse


}