package com.shahzar.enoclink.data.mock

import android.util.Log
import com.shahzar.enoclink.data.local.UserPreferences
import com.shahzar.enoclink.data.model.request.AvatarRequest
import com.shahzar.enoclink.data.model.request.LoginRequest
import com.shahzar.enoclink.data.model.response.AvatarResponse
import com.shahzar.enoclink.data.model.response.LoginResponse
import com.shahzar.enoclink.data.model.response.UserResponse
import com.shahzar.enoclink.data.remote.ApiService
import kotlinx.coroutines.delay

class MockApiServiceImpl(val prefs: UserPreferences): ApiService {

    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        Log.d("MockApiServiceImpl", "Password: ${loginRequest.password}")
        return LoginResponse("29837EHD789OK23HU8I", "12345")
    }

    override suspend fun getUserDetails(userId: String): UserResponse {
        return UserResponse(prefs.getUserEmail()?:"", "")
    }

    override suspend fun uploadUserAvatar(userId: String, avatarRequest: AvatarRequest): AvatarResponse {
        Log.d("MockApiServiceImpl", "uploadUserAvatar : " + avatarRequest.avatarBase64)
        delay(2000)
        return AvatarResponse("https://images.pexels.com/photos/5571038/pexels-photo-5571038.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260")
    }
}