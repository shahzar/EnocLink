package com.shahzar.enoclink.data

import com.shahzar.enoclink.data.remote.GravatarApiService
import com.shahzar.enoclink.util.hash.HashUtils
import java.lang.Exception
import javax.inject.Inject

class GravatarRepository @Inject constructor(
    private val remoteDataSrc: GravatarApiService
): BaseRepository() {

    suspend fun getImageUrl(email: String) = safeApiCall {
        val response = remoteDataSrc.getImage(HashUtils.md5(email) + ".json")

        response.entry.firstOrNull()?.photos?.firstOrNull()?.value?.let { url ->

            return@safeApiCall "$url?s=200"

        }?: throw Exception("No image found")

    }


}