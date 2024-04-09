@file:Suppress("unused")

package com.karimwahdan.rsh.controllers


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karimwahdan.rsh.app.UiState
import com.karimwahdan.rsh.app.config.network.ErrorResponseParser
import com.karimwahdan.rsh.routes.Callers
import com.karimwahdan.rsm.ErrorResponse
import com.karimwahdan.rsm.notifications.ProjectNotificationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call

class NotificationsController: ViewModel() {

    val data = MutableLiveData<UiState<ProjectNotificationResponse>>()
    val state: LiveData<UiState<ProjectNotificationResponse>> get() = data
    private val api= Callers().notificationsApi()
    fun index(projectId:Int) {

        viewModelScope.launch(Dispatchers.IO) {
            val call: Call<ProjectNotificationResponse> =
                api.index(projectId)
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
        }
    }
}