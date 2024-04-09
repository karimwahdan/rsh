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
import com.karimwahdan.rsm.customer.DeliveryAddressesResponse
import com.karimwahdan.rsm.customer.SingleDeliveryAddressResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call

class AddressController : ViewModel() {
    private val data = MutableLiveData<UiState<SingleDeliveryAddressResponse>>()
    val state: LiveData<UiState<SingleDeliveryAddressResponse>> get() = data

    private val allData = MutableLiveData<UiState<DeliveryAddressesResponse>>()
    val allDataState: LiveData<UiState<DeliveryAddressesResponse>> get() = allData
    val api = Callers().addressesApi()

    fun index(projectId:Int, customerId: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            val call: Call<DeliveryAddressesResponse> = api.index(projectId,customerId)
            try {
                val response = call.execute()
                withContext(Dispatchers.Main) {

                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            allData.value = UiState.Success(body)
                        } else if (response.errorBody() != null) {
                            val errorBody = response.errorBody()!!.string()
                            val errorResponse = ErrorResponseParser().parseError(errorBody)
                            if (errorResponse!=null){
                                allData.value=UiState.Error(errorResponse)
                            }else{
                                allData.value=UiState.Error(ErrorResponse(errorBody,null))
                            }
                            return@withContext
                        }
                    } else if (response.errorBody() != null) {
                        val errorBody = response.errorBody()!!.string()
                        val errorResponse = ErrorResponseParser().parseError(errorBody)
                        if (errorResponse!=null){
                            allData.value=UiState.Error(errorResponse)
                        }else{
                            allData.value=UiState.Error(ErrorResponse(errorBody,null))
                        }
                        return@withContext
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun store(projectId:Int, customerId:Int,
              name: String, mobileNo: String, altMobileNo: String?,
              addressDetails: String, landmark: String, countryId: Int,
              countryName: String, cityId: Int, cityName: String, areaId: Int?,
              areaName: String, type: String, defaultAddress: Boolean
    ) {

        viewModelScope.launch(Dispatchers.IO) {
            val call: Call<SingleDeliveryAddressResponse> = api.store(
                projectId=projectId,
                customerId=customerId,
                name = name,
                mobileNo = mobileNo,
                altMobileNo = altMobileNo,
                address = addressDetails,
                countryId = countryId,
                countryName = countryName,
                cityId = cityId,
                cityName = cityName,
                areaId = areaId,
                areaName = areaName, type = type,
                default = if (defaultAddress) {
                    1
                } else {
                    0
                },
                landmark = landmark,
                latitude = null, longitude = null
            )
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