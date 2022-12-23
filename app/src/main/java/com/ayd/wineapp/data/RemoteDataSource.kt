package com.ayd.wineapp.data

import com.ayd.wineapp.data.network.WineApi
import com.ayd.wineapp.model.RedWine
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val wineApi: WineApi
) {

    suspend fun getRedWine(queries: Map<String,String>): Response<RedWine> {
        return wineApi.getWines(queries)
    }

}