@file:Suppress("unused")

package com.karimwahdan.revitstorehelpers.controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karimwahdan.revitstorehelpers.app.UiState
import com.karimwahdan.revitstorehelpers.app.config.network.ErrorResponseParser
import com.karimwahdan.revitstorehelpers.routes.Callers
import com.karimwahdan.rsm.ErrorResponse
import com.karimwahdan.rsm.product.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call

class SearchController : ViewModel() {
    private val data = MutableLiveData<UiState<ProductResponse>>()
    val state: LiveData<UiState<ProductResponse>> get() = data
    private val api = Callers().productApi()

    fun search(projectId:Int,name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val call: Call<ProductResponse> =
                api.search(project_id = projectId, name = name)
            val response = call.execute()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            data.value = UiState.Success(body)

                        } else if (response.errorBody() != null) {
                            val errorBody = response.errorBody()!!.string()
                            val errorResponse = ErrorResponseParser().parseError(errorBody)
                            if (errorResponse!=null){
                                data.value=UiState.Error(errorResponse)
                            }else{
                                data.value=UiState.Error(ErrorResponse(errorBody,null))
                            }
                            return@withContext
                        }
                    } else if (response.errorBody() != null) {
                        val errorBody = response.errorBody()!!.string()
                        val errorResponse = ErrorResponseParser().parseError(errorBody)
                        if (errorResponse!=null){
                            data.value=UiState.Error(errorResponse)
                        }else{
                            data.value=UiState.Error(ErrorResponse(errorBody,null))
                        }
                        return@withContext
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            /*
             if(networkAvailable){}else{withContext(Dispatchers.Main){data.value= noInternetState}}
             */
        }
    }
}