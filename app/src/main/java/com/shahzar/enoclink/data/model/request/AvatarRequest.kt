package com.shahzar.enoclink.data.model.request
import com.google.gson.annotations.SerializedName


data class AvatarRequest(
    @SerializedName("avatar")
    var avatarBase64: String = ""
)