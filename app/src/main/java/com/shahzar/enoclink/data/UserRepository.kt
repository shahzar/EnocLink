package com.shahzar.enoclink.data

import com.shahzar.enoclink.data.local.UserPreferences
import com.shahzar.enoclink.data.model.request.AvatarRequest
import com.shahzar.enoclink.data.model.request.LoginRequest
import com.shahzar.enoclink.data.remote.ApiService
import com.shahzar.enoclink.util.interceptor.SessionInterceptor
import javax.inject.Inject
import javax.inject.Named

class UserRepository @Inject constructor(
    @Named("MockApi") val remoteDataSrc: ApiService,
    private val prefs: UserPreferences,
    private val sessionInterceptor: SessionInterceptor
): BaseDataManager() {

    suspend fun getSampleData() = safeApiCall {
        remoteDataSrc.getSampleData()
    }

    suspend fun userLogin(username: String, password: String) = safeApiCall {
        val response = remoteDataSrc.login(LoginRequest(username, password))
        if (response.token.isNotEmpty()) {
            sessionInterceptor.setSessionToken(response.token)
            prefs.setAccessToken(response.token)
            prefs.setUserId(response.token)
            prefs.setUserEmail(username)
        }
        return@safeApiCall response
    }

    suspend fun getUserDetails(userId: String) = safeApiCall {
        remoteDataSrc.getUserDetails(userId)
    }

    suspend fun uploadAvatar(userId: String, avatarRequest: AvatarRequest) = safeApiCall {
        remoteDataSrc.uploadUserAvatar(userId, avatarRequest)
    }
}