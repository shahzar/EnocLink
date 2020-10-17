package com.shahzar.enoclink.data.model.response
import com.google.gson.annotations.SerializedName


data class AvatarResponse(
    @SerializedName("avatar_url")
    var avatarUrl: String = ""
)