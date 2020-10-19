package com.shahzar.enoclink.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shahzar.enoclink.data.UserRepository
import com.shahzar.enoclink.data.local.UserPreferences
import com.shahzar.enoclink.ui.base.BaseViewModel
import com.shahzar.enoclink.util.Constants
import com.shahzar.enoclink.util.encrypt.EncryptUtil
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
                 userRepository.userLogin(username, EncryptUtil.aesEncrypt(password))
             },
             onSuccess = {
                 if (it.token.isNotEmpty()) {
                     _onLoginSuccess.value = Event(true)
                 }
             }
         )

     }



}
