package com.ayd.wineapp.data.network

import com.ayd.wineapp.model.Wine
import com.ayd.wineapp.utils.Constants.Companion.GET_URL_RED
import com.ayd.wineapp.utils.Constants.Companion.GET_URL_ROSE
import com.ayd.wineapp.utils.Constants.Companion.GET_URL_SPARKLING
import com.ayd.wineapp.utils.Constants.Companion.GET_URL_WHITE
import retrofit2.Response
import retrofit2.http.GET

interface WineApi {

    @GET(GET_URL_RED)
    suspend fun getRedWines(
        //@QueryMap queries: Map<String,String>
    ):Response<Wine>

    @GET(GET_URL_WHITE)
    suspend fun getWhiteWines(
        //@QueryMap queries: Map<String,String>
    ):Response<Wine>

    @GET(GET_URL_SPARKLING)
    suspend fun getSparklingWines(
    ):Response<Wine>

    @GET(GET_URL_ROSE)
    suspend fun getRoseWines(
    ):Response<Wine>


}