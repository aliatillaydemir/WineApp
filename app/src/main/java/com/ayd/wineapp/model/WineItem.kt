package com.ayd.wineapp.model


import com.google.gson.annotations.SerializedName

data class WineItem(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("rating")
    val rating: Rating?,
    @SerializedName("wine")
    val wine: String?,
    @SerializedName("winery")
    val winery: String?
)