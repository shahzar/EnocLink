package com.shahzar.enoclink.data.model.response
import com.google.gson.annotations.SerializedName


data class UserResponse(
    @SerializedName("email")
    var email: String = "",
    @SerializedName("password")
    var password: String = "",
    @SerializedName("avatar_url")
    var avatarUrl: String = ""
)