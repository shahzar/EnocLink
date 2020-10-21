package com.shahzar.enoclink.data

import com.shahzar.enoclink.data.local.UserPreferences
import com.shahzar.enoclink.data.model.request.AvatarRequest
import com.shahzar.enoclink.data.model.request.LoginRequest
import com.shahzar.enoclink.data.remote.ApiService
import com.shahzar.enoclink.util.interceptor.SessionInterceptor
import javax.inject.Inject
import javax.inject.Named

class UserRepository @Inject constructor(
    @Named("MockApi")
    private val remoteDataSrc: ApiService,
    private val prefs: UserPreferences,
    private val sessionInterceptor: SessionInterceptor
): BaseRepository() {

    fun setSession(token: String) {
        sessionInterceptor.setSessionToken(token)
    }

    suspend fun userLogin(username: String, password: String) = safeApiCall {
        val response = remoteDataSrc.login(LoginRequest(username, password))
        if (response.token.isNotEmpty()) {
            setSession(response.token)
            prefs.setAccessToken(response.token)
            prefs.setUserId(response.userid)
            prefs.setUserEmail(username)
            // Not recommended way to store locally, check readme
            prefs.setUserPassword(password)
        }
        return@safeApiCall response
    }

    suspend fun getUserDetails(userId: String) = safeApiCall {
        val response = remoteDataSrc.getUserDetails(userId)
        response.password = prefs.getUserPassword()?:""
        return@safeApiCall response
    }

    suspend fun uploadAvatar(userId: String, avatarRequest: AvatarRequest) = safeApiCall {
        remoteDataSrc.uploadUserAvatar(userId, avatarRequest)
    }
}