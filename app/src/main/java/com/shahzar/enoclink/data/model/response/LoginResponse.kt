package com.shahzar.enoclink.data.model.response
import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("token")
    var token: String = "",
    @SerializedName("userid")
    var userid: String = ""
)