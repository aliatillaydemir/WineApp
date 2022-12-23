package com.ayd.wineapp.model


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("average")
    val average: String?,
    @SerializedName("reviews")
    val reviews: String?
)