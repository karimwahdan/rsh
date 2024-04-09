@file:Suppress("unused")

package com.karimwahdan.revitstorehelpers.controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karimwahdan.revitstorehelpers.app.UiState
import com.karimwahdan.revitstorehelpers.app.config.network.ErrorResponseParser
import com.karimwahdan.rsm.ErrorResponse
import com.karimwahdan.rsm.faq.FAQResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import com.karimwahdan.revitstorehelpers.routes.Callers

class FaqController : ViewModel() {
    private val api= Callers().faqApi()
    val data = MutableLiveData<UiState<FAQResponse>>()
    val state: LiveData<UiState<FAQResponse>> get() = data
    fun index(projectId:Int) {
        val call: Call<FAQResponse> = api.index(projectId)

        viewModelScope.launch(Dispatchers.IO) {
            val response = call.execute()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val body = response.body()
                        val responseErrorBody=response.errorBody()
                        if (body != null) {
                            data.value = UiState.Success(body)
                        } else if (responseErrorBody != null) {
                            val errorBody = responseErrorBody.string()
                            val errorResponse = ErrorResponseParser().parseError(errorBody)
                            if (errorResponse!=null){
                                data.value= UiState.Error(errorResponse)
                            }else{
                                data.value= UiState.Error(ErrorResponse(errorBody,null))
                            }
                            return@withContext
                        }
                    } else{
                        val responseErrorBody=response.errorBody()
                        if (responseErrorBody!=null){
                            val errorBody = responseErrorBody.string()
                            val errorResponse = ErrorResponseParser().parseError(errorBody)
                            if (errorResponse!=null){
                                data.value= UiState.Error(errorResponse)
                            }else{
                                data.value= UiState.Error(ErrorResponse(errorBody,null))
                            }
                        }
                        return@withContext
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}