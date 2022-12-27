package com.ayd.wineapp.data.network

import com.ayd.wineapp.model.Wine
import retrofit2.Response
import retrofit2.http.GET

interface WineApi {

    @GET("/wines/reds")
    suspend fun getRedWines(
        //@QueryMap queries: Map<String,String>
    ):Response<Wine>

    @GET("/wines/whites")
    suspend fun getWhiteWines(
        //@QueryMap queries: Map<String,String>
    ):Response<Wine>

    @GET("/wines/sparkling")
    suspend fun getSparklingWines(
    ):Response<Wine>

    @GET("/wines/rose")
    suspend fun getRoseWines(
    ):Response<Wine>


}