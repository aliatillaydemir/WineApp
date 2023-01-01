package com.ayd.wineapp.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.RawValue
import kotlinx.parcelize.Parcelize

@Parcelize
data class WineItem(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("rating")
    val rating: @RawValue Rating?,
    @SerializedName("wine")
    val wine: String?,
    @SerializedName("winery")
    val winery: String?
): Parcelable