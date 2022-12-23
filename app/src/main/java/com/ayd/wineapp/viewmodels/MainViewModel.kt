package com.ayd.wineapp.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ayd.wineapp.data.Repository
import com.ayd.wineapp.model.RedWine
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

    var wineResponse: MutableLiveData<NetworkResult<RedWine>> = MutableLiveData()

    fun getWine(queries: Map<String,String>) = viewModelScope.launch {
        getWineSafeCall(queries)
    }

    private suspend fun getWineSafeCall(queries: Map<String, String>) {
        wineResponse.value = NetworkResult.Loading()
        if(connectionInternet()){
            try {
                val response = repository.remote.getRedWine(queries)
                wineResponse.value = handleProductResponse(response)

/*                val product = wineResponse.value!!.data
                if(product != null){
                    offlineCacheProducts(product)
                }*/

            }catch (e: Exception){
                wineResponse.value = NetworkResult.Error("no internet")
            }
        }else{
            wineResponse.value = NetworkResult.Error("No internet Connection")
        }
    }

    private fun handleProductResponse(response: Response<RedWine>): NetworkResult<RedWine>? {
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