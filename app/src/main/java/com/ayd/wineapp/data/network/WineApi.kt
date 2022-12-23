package com.ayd.wineapp.data.network

import com.ayd.wineapp.model.RedWine
import com.ayd.wineapp.model.WhiteWine
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WineApi {

    @GET("/wines/reds")
    suspend fun getWines(
        @QueryMap queries: Map<String,String>
    ):Response<RedWine>

    @GET("/wines/whites")
    suspend fun getWinesWhite(
        @QueryMap queries: Map<String,String>
    ):Response<WhiteWine>


}