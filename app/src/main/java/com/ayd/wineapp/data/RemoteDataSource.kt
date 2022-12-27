package com.ayd.wineapp.data

import com.ayd.wineapp.data.network.WineApi
import com.ayd.wineapp.model.Wine
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val wineApi: WineApi
) {

    suspend fun getRedWine(queries: Map<String,String>): Response<Wine> {
        return wineApi.getRedWines()
    }

    suspend fun getWhiteWine(queries: Map<String,String>): Response<Wine> {
        return wineApi.getWhiteWines()
    }

    suspend fun getSparklingWine(queries: Map<String,String>): Response<Wine> {
        return wineApi.getSparklingWines()
    }

    suspend fun getRoseWine(queries: Map<String,String>): Response<Wine> {
        return wineApi.getRoseWines()
    }

}