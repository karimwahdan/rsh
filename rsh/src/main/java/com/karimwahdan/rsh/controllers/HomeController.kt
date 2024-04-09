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
import com.karimwahdan.rsm.home.HomeDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call

class HomeController : ViewModel() {
    private val data = MutableLiveData<UiState<HomeDataModel>>()
    val state: LiveData<UiState<HomeDataModel>> get() = data
    private val api = Callers().homeApi()
    //private val connectionCheck = ConnectionCheck(Instance.context)

    fun getHome(projectId:Int) {

        viewModelScope.launch(Dispatchers.IO) {
            val call: Call<HomeDataModel> = api.home(projectId)
            try {
                val response = call.execute()
                withContext(Dispatchers.Main) {
                    try {
                        if (response.isSuccessful) {
                            val body = response.body()
                            if (body != null) {
                                data.value = UiState.Success(body)
                                //initPusher(body)
                                //setDesignSettings(body)
                            } else if (response.errorBody() != null) {
                                val errorBody = response.errorBody()!!.string()
                                setHomeControllerError(errorBody)
                                return@withContext
                            }
                        } else if (response.errorBody() != null) {
                            val errorBody = response.errorBody()!!.string()
                            setHomeControllerError(errorBody)
                            return@withContext
                        }
                    } catch (_: Exception) {
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    //data.value = ConnectionCheck().noInternetState
                }
            }
        }
    }
    private fun setHomeControllerError( errorBody: String) {
        val errorResponse = ErrorResponseParser().parseError(errorBody)
        if (errorResponse!=null){
            data.value=UiState.Error(errorResponse)
        }else{
            data.value=UiState.Error(ErrorResponse(errorBody,null))
        }
    }

}