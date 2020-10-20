package com.shahzar.enoclink.ui.home

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shahzar.enoclink.data.GravatarRepository
import com.shahzar.enoclink.data.UserRepository
import com.shahzar.enoclink.data.local.UserPreferences
import com.shahzar.enoclink.data.model.request.AvatarRequest
import com.shahzar.enoclink.data.model.response.UserResponse
import com.shahzar.enoclink.ui.base.BaseViewModel
import com.shahzar.enoclink.util.ImageUtils
import javax.inject.Inject

class ProfileViewModel @Inject constructor (
    private val userRepository: UserRepository,
    private val gravatarRepository: GravatarRepository,
    private val prefs: UserPreferences) : BaseViewModel(){

    private val _profileImageUri = MutableLiveData<Uri>()
    private val _profileImageUrl = MutableLiveData<String>()
    private val _user = MutableLiveData<UserResponse>()

    val profileImageUri: LiveData<Uri>
        get() = _profileImageUri

    val profileImageUrl: LiveData<String>
        get() = _profileImageUrl

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
                getGravatar()
            }
        )

    }

    fun getGravatar() = user.value?.let{ user ->

        if (user.avatarUrl.isNotEmpty()) {
           return@let
        }

        ioLaunch(
            block = {
                gravatarRepository.getImageUrl(user.email)
            },
            onSuccess = {
                user.avatarUrl = it
                _user.value = user
            },
            onFailure = {}
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
            },
            onSuccess = {
                _profileImageUrl.value = it.avatarUrl
            }
        )
    }

}
