package com.test.productsearch.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.productsearch.data.model.APIResponse
import com.test.productsearch.data.util.Resource
import com.test.productsearch.domain.usescases.GetSearchedSearchProductUseCase
import kotlinx.coroutines.launch

class SearchProcuctViewModel(
    private val app: Application,
    private val getSearchedSearchProductUseCase: GetSearchedSearchProductUseCase
): AndroidViewModel(app) {

    val searchproduct: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun searchProduct( searchQuery: String, page: Int) = viewModelScope.launch {
        searchproduct.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val response = getSearchedSearchProductUseCase.execute(
                    searchQuery,
                    page
                )
                searchproduct.postValue(response)
            }else{
                searchproduct.postValue(Resource.Error("No internet connection"))
            }
        }catch (e:Exception){
            searchproduct.postValue(Resource.Error(e.message))}
    }


    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        if (connectivityManager == null) return false

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            capabilities?.run {
                when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } ?: false
        } else {
            @Suppress("DEPRECATION")
            connectivityManager.activeNetworkInfo?.run {
                isConnected && (type == ConnectivityManager.TYPE_WIFI || type == ConnectivityManager.TYPE_MOBILE)
            } ?: false
        }
    }
}