package com.shahzar.enoclink.data.mock

import android.util.Log
import com.shahzar.enoclink.data.model.SampleDataModel
import com.shahzar.enoclink.data.model.request.AvatarRequest
import com.shahzar.enoclink.data.model.request.LoginRequest
import com.shahzar.enoclink.data.model.response.AvatarResponse
import com.shahzar.enoclink.data.model.response.LoginResponse
import com.shahzar.enoclink.data.model.response.UserResponse
import com.shahzar.enoclink.data.remote.ApiService
import retrofit2.http.Body

class MockApiServiceImpl: ApiService {

    override suspend fun getSampleData(): SampleDataModel {
        return SampleDataModel(true, 0, "Title", 0)
    }

    override suspend fun login(@Body loginRequest: LoginRequest): LoginResponse {
        return LoginResponse("29837EHD789OK23HU8I", "12345")
    }

    override suspend fun getUserDetails(userId: String): UserResponse {
        return UserResponse("shahzar@outlook.com", "https://images.pexels.com/photos/5571038/pexels-photo-5571038.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260")
    }

    override suspend fun uploadUserAvatar(userId: String, avatarRequest: AvatarRequest): AvatarResponse {
        Log.d("MockApiServiceImpl", "uploadUserAvatar : " + avatarRequest.avatarBase64)
        return AvatarResponse("https://images.pexels.com/photos/5571038/pexels-photo-5571038.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260")
    }
}