package com.ayd.wineapp.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ayd.wineapp.data.Repository
import com.ayd.wineapp.model.Wine
import com.ayd.wineapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
): AndroidViewModel(application) {

    var wineResponse: MutableLiveData<NetworkResult<Wine>> = MutableLiveData()

    fun getRedWine(queries: Map<String,String>) = viewModelScope.launch {
        getWineRedSafeCall(queries)
    }

    fun getWhiteWine(queries: Map<String,String>) = viewModelScope.launch {
        getWineWhiteSafeCall(queries)
    }

    fun getSparklingWine(queries: Map<String, String>) = viewModelScope.launch {
        getWineSparklingSafeCall(queries)
    }

    fun getRoseWine(queries: Map<String, String>) = viewModelScope.launch {
        getWineRoseSafeCall(queries)
    }

    private suspend fun getWineRedSafeCall(queries: Map<String, String>) {
        wineResponse.value = NetworkResult.Loading()
        if(connectionInternet()){
            try {
                val response = repository.remote.getRedWine(queries)
                wineResponse.value = handleProductResponse(response)

            }catch (e: Exception){
                wineResponse.value = NetworkResult.Error("no internet")
            }
        }else{
            wineResponse.value = NetworkResult.Error("No internet Connection")
        }
    }

    private suspend fun getWineWhiteSafeCall(queries: Map<String, String>) {
        wineResponse.value = NetworkResult.Loading()
        if(connectionInternet()){
            try {
                val response = repository.remote.getWhiteWine(queries)
                wineResponse.value = handleProductResponse(response)

            }catch (e: Exception){
                wineResponse.value = NetworkResult.Error("no internet")
            }
        }else{
            wineResponse.value = NetworkResult.Error("No internet Connection")
        }
    }

    private suspend fun getWineSparklingSafeCall(queries: Map<String, String>) {
        wineResponse.value = NetworkResult.Loading()
        if(connectionInternet()){
            try {
                val response = repository.remote.getSparklingWine(queries)
                wineResponse.value = handleProductResponse(response)

            }catch (e: Exception){
                wineResponse.value = NetworkResult.Error("no internet")
            }
        }else{
            wineResponse.value = NetworkResult.Error("No internet Connection")
        }
    }

    private suspend fun getWineRoseSafeCall(queries: Map<String, String>) {
        wineResponse.value = NetworkResult.Loading()
        if(connectionInternet()){
            try {
                val response = repository.remote.getRoseWine(queries)
                wineResponse.value = handleProductResponse(response)

            }catch (e: Exception){
                wineResponse.value = NetworkResult.Error("no internet")
            }
        }else{
            wineResponse.value = NetworkResult.Error("No internet Connection")
        }
    }


            /** handling and connections  */

    private fun handleProductResponse(response: Response<Wine>): NetworkResult<Wine>? {
        when{
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("Key fail!")
            }
            response.body()!!.isNullOrEmpty() -> {
                return NetworkResult.Error("product not found")
            }
            response.isSuccessful -> {
                val product = response.body()
                return NetworkResult.Success(product!!)
            }
            else ->{
                return NetworkResult.Error(response.message())
            }
        }
    }


    private fun connectionInternet():Boolean{
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when{
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

}