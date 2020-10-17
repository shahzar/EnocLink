package com.shahzar.enoclink.data.model
import com.google.gson.annotations.SerializedName
import com.shahzar.enoclink.data.model.BaseModel


data class SampleDataModel(
    @SerializedName("completed")
    var completed: Boolean = false,
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("title")
    var title: String = "",
    @SerializedName("userId")
    var userId: Int = 0
): BaseModel() {

    override fun toString(): String {
        return super.toString()
    }
}