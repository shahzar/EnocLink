package com.shahzar.enoclink.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shahzar.enoclink.data.UserRepository
import com.shahzar.enoclink.data.local.UserPreferences
import com.shahzar.enoclink.data.model.SampleDataModel
import com.shahzar.enoclink.ui.base.BaseViewModel
import com.shahzar.enoclink.util.hash.HashUtils
import com.shahzar.enoclink.util.livedata.Event
import javax.inject.Inject

class LoginViewModel @Inject constructor (
    private val userRepository: UserRepository,
    private val prefs: UserPreferences) : BaseViewModel(){

    private val _onLoginSuccess = MutableLiveData<Event<Boolean>>()

    val onLoginSuccess: LiveData<Event<Boolean>>
        get() = _onLoginSuccess

     fun login(username: String, password: String) {

         ioLaunch(
             block = {
                 userRepository.userLogin(username, HashUtils.md5(password))
             },
             onSuccess = {
                 if (it.token.isNotEmpty()) {
                     prefs.setAccessToken(it.token)
                     prefs.setUserId(it.token)
                     prefs.setUserEmail(username)
                     _onLoginSuccess.value = Event(true)
                 }
             }
         )

     }



}
