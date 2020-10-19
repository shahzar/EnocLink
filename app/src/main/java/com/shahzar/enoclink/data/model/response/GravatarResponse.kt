package com.shahzar.enoclink.data.model.response
import com.google.gson.annotations.SerializedName


data class GravatarResponse(
    @SerializedName("entry")
    var entry: List<Entry> = listOf()
)

data class Entry(
    @SerializedName("displayName")
    var displayName: String = "",
    @SerializedName("hash")
    var hash: String = "",
    @SerializedName("id")
    var id: String = "",
    @SerializedName("name")
    var name: List<Any> = listOf(),
    @SerializedName("photos")
    var photos: List<Photo> = listOf(),
    @SerializedName("preferredUsername")
    var preferredUsername: String = "",
    @SerializedName("profileUrl")
    var profileUrl: String = "",
    @SerializedName("requestHash")
    var requestHash: String = "",
    @SerializedName("thumbnailUrl")
    var thumbnailUrl: String = "",
    @SerializedName("urls")
    var urls: List<Any> = listOf()
)

data class Photo(
    @SerializedName("type")
    var type: String = "",
    @SerializedName("value")
    var value: String = ""
)