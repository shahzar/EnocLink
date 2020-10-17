package com.shahzar.enoclink.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shahzar.enoclink.data.UserRepository
import com.shahzar.enoclink.data.local.UserPreferences
import com.shahzar.enoclink.ui.base.BaseViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor (
    private val userRepository: UserRepository,
    private val prefs: UserPreferences
    ) : BaseViewModel(){

    private val _isUserLoggedIn = MutableLiveData<Boolean>()

    val isUserLoggedIn: LiveData<Boolean>
        get() = _isUserLoggedIn

     fun checkLoginStatus() {
         ioLaunch({
             prefs.getAccessToken()
         }, {
             _isUserLoggedIn.value = it != null
         })
     }



}
