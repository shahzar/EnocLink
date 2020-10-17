package com.shahzar.enoclink.data.model

import com.google.gson.annotations.SerializedName

data class CategoryModel(
    @SerializedName("count")
    var count: Int = 0,
    @SerializedName("description")
    var description: String = "",
    @SerializedName("display")
    var display: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("image")
    var image: Image? = Image(),
    @SerializedName("_links")
    var links: Links = Links(),
    @SerializedName("menu_order")
    var menuOrder: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("parent")
    var parent: Int = 0,
    @SerializedName("slug")
    var slug: String = ""
)

data class Image(
    @SerializedName("alt")
    var alt: String = "",
    @SerializedName("date_created")
    var dateCreated: String = "",
    @SerializedName("date_created_gmt")
    var dateCreatedGmt: String = "",
    @SerializedName("date_modified")
    var dateModified: String = "",
    @SerializedName("date_modified_gmt")
    var dateModifiedGmt: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("src")
    var src: String = ""
)

data class Links(
    @SerializedName("collection")
    var collection: List<Collection> = listOf(),
    @SerializedName("self")
    var self: List<Self> = listOf()
)

data class Collection(
    @SerializedName("href")
    var href: String = ""
)

data class Self(
    @SerializedName("href")
    var href: String = ""
)