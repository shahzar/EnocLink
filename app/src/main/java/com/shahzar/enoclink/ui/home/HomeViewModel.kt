package com.shahzar.enoclink.ui.home

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shahzar.enoclink.data.DataManager
import com.shahzar.enoclink.data.model.SampleDataModel
import com.shahzar.enoclink.di.components.AppComponent
import com.shahzar.enoclink.ui.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor (val dataManager: DataManager) : BaseViewModel(){

    private val _sampleData = MutableLiveData<SampleDataModel>()
    private val _profileImageUri = MutableLiveData<Uri>()

    val sampleData: LiveData<SampleDataModel>
        get() = _sampleData

    val profileImageUri: LiveData<Uri>
        get() = _profileImageUri


    fun getSampleData() {

        ioLaunch(
            block = {
                dataManager.getSampleData()
            },
            onSuccess = {
                _sampleData.value = it
            }
        )

    }

    fun setSelectedImage(fileUri: Uri?) {
        fileUri?.let {
            _profileImageUri.value = it
        }
    }

}
