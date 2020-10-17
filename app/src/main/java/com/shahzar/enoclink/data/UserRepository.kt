package com.shahzar.enoclink.data

import android.util.Log
import com.shahzar.enoclink.data.model.request.AvatarRequest
import com.shahzar.enoclink.data.model.request.LoginRequest
import com.shahzar.enoclink.data.remote.ApiService
import javax.inject.Inject
import javax.inject.Named

class UserRepository @Inject constructor(@Named("MockApi") val remoteDataSrc: ApiService): BaseDataManager() {

    suspend fun getSampleData() = safeApiCall {
        remoteDataSrc.getSampleData()
    }

    suspend fun userLogin(username: String, password: String) = safeApiCall {
        remoteDataSrc.login(LoginRequest(username, password))
    }

    suspend fun getUserDetails(userId: String) = safeApiCall {
        remoteDataSrc.getUserDetails(userId)
    }

    suspend fun uploadAvatar(userId: String, avatarRequest: AvatarRequest) = safeApiCall {
        remoteDataSrc.uploadUserAvatar(userId, avatarRequest)
    }
}