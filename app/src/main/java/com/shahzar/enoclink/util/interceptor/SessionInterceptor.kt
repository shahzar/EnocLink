package com.shahzar.enoclink.util.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionInterceptor @Inject constructor() : Interceptor {
    private var sessionToken: String? = null

    fun setSessionToken(sessionToken: String?) {
        this.sessionToken = sessionToken
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder()
        if (request.header("Authorization") == null) {
            // needs credentials
            sessionToken?.let {
                requestBuilder.addHeader("Authorization", "Bearer $it")
            }
        }
        return chain.proceed(requestBuilder.build())
    }
}