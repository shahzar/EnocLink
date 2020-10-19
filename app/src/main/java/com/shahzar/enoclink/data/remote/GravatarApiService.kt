package com.shahzar.enoclink.data.remote

import com.shahzar.enoclink.data.model.BaseResponse
import com.shahzar.enoclink.data.model.response.GravatarResponse
import retrofit2.http.POST
import retrofit2.http.Path

interface GravatarApiService {

    @POST("{emailHash}")
        suspend fun getImage(@Path("emailHash") emailHash: String): GravatarResponse


}