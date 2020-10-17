package com.shahzar.enoclink.ui.home

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shahzar.enoclink.data.UserRepository
import com.shahzar.enoclink.data.local.UserPreferences
import com.shahzar.enoclink.data.model.SampleDataModel
import com.shahzar.enoclink.data.model.request.AvatarRequest
import com.shahzar.enoclink.data.model.response.UserResponse
import com.shahzar.enoclink.ui.base.BaseViewModel
import com.shahzar.enoclink.util.ImageUtils
import javax.inject.Inject

class HomeViewModel @Inject constructor (
    private val userRepository: UserRepository,
    private val prefs: UserPreferences) : BaseViewModel(){

    private val _profileImageUri = MutableLiveData<Uri>()
    private val _user = MutableLiveData<UserResponse>()

    val profileImageUri: LiveData<Uri>
        get() = _profileImageUri

    val user: LiveData<UserResponse>
        get() = _user


    fun getUserDetails() {

        val userId = prefs.getUserId()?: return

        ioLaunch(
            block = {
                userRepository.getUserDetails(userId)
            },
            onSuccess = {
                _user.value = it
            }
        )

    }

    fun setSelectedImage(fileUri: Uri?) {

        val userId = prefs.getUserId()?: return
        val imageBase64 = ImageUtils.toBase64(fileUri?.path)?: return

        fileUri?.let {
            _profileImageUri.value = it
        }

        ioLaunch(
            block = {
                userRepository.uploadAvatar(userId, AvatarRequest(imageBase64))
            }
        )
    }

}
