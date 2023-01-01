package com.ayd.wineapp.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating(
    @SerializedName("average")
    val average: String?,
    @SerializedName("reviews")
    val reviews: String?
): Parcelable