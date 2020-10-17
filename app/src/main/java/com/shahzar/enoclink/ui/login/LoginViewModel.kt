package com.shahzar.enoclink.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shahzar.enoclink.data.DataManager
import com.shahzar.enoclink.data.model.SampleDataModel
import com.shahzar.enoclink.di.components.AppComponent
import com.shahzar.enoclink.ui.base.BaseViewModel
import com.shahzar.enoclink.util.livedata.Event
import com.shahzar.enoclink.util.livedata.Resource
import com.shahzar.enoclink.util.livedata.Status
import javax.inject.Inject

class LoginViewModel @Inject constructor (val dataManager: DataManager) : BaseViewModel(){

    private val _sampleData = MutableLiveData<SampleDataModel>()
    private val _onLoginSuccess = MutableLiveData<Event<Boolean>>()

    val sampleData: LiveData<SampleDataModel>
        get() = _sampleData

    val onLoginSuccess: LiveData<Event<Boolean>>
        get() = _onLoginSuccess

     fun login() {
         _onLoginSuccess.value = Event(true)
     }



}
