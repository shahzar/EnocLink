package com.shahzar.enoclink.ui.splash

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

class SplashViewModel @Inject constructor (private val dataManager: DataManager) : BaseViewModel(){

    private val _isUserLoggedIn = MutableLiveData<Boolean>()

    val isUserLoggedIn: LiveData<Boolean>
        get() = _isUserLoggedIn

     fun checkLoginStatus() {
         _isUserLoggedIn.value = true
     }



}
