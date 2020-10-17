package com.shahzar.enoclink.data.model.response
import com.google.gson.annotations.SerializedName


data class UserResponse(
    @SerializedName("email")
    var email: String = "",
    @SerializedName("avatar_url")
    var avatarUrl: String = ""
)