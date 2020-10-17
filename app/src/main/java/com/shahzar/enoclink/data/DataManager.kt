package com.shahzar.enoclink.data

import com.shahzar.enoclink.data.repository.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataManager @Inject constructor(val remoteDataSrc: ApiService): BaseDataManager() {

    suspend fun getSampleData() = safeApiCall {
        remoteDataSrc.getSampleData()
    }
}