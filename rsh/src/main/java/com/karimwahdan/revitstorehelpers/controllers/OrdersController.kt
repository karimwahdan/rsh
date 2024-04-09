@file:Suppress("unused")

package com.karimwahdan.revitstorehelpers.controllers

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karimwahdan.revitstorehelpers.app.UiState
import com.karimwahdan.revitstorehelpers.app.config.network.ErrorResponseParser
import com.karimwahdan.revitstorehelpers.routes.Callers
import com.karimwahdan.revitstorehelpers.routes.interfaces.requestBodies.OrderRequestBody
import com.karimwahdan.rsm.ErrorResponse
import com.karimwahdan.rsm.cart.CartItem
import com.karimwahdan.rsm.orders.AllOrdersResponse
import com.karimwahdan.rsm.orders.Order
import com.karimwahdan.rsm.orders.OrderResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call

class OrdersController: ViewModel() {
    @Suppress("PrivatePropertyName")
    private val ORDER_PENDING=2
    @Suppress("MemberVisibilityCanBePrivate", "PropertyName")
    val ORDER_DONE=1
    @Suppress("MemberVisibilityCanBePrivate", "PropertyName", "PropertyName")
    val ORDER_NOT_COMPLETED=0
    val data = MutableLiveData<UiState<AllOrdersResponse>>()
    val state: LiveData<UiState<AllOrdersResponse>> get() = data

    private val datum = MutableLiveData<UiState<OrderResponse>> ()
    val singleState: LiveData<UiState<OrderResponse>> get()= datum
    @Suppress("MemberVisibilityCanBePrivate")
    val orderStatus= mutableIntStateOf(ORDER_PENDING)
    private val ordersApi= Callers().ordersApi()

    fun index(projectId:Int,customerId: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            val call = ordersApi.index(projectId= projectId,customerId = customerId)
            try {
                val response = call.execute()
                withContext(Dispatchers.Main) {
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
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun createOrder(projectId:Int,customerId:Int,
                    taxValue:Float?,
                    serviceValue:Float?,
                    promoCode:String?,
                    promoDiscount:Float?,
                    totalValue:Float?,
                    cityId:Int?,
                    deliveryType:String?,
                    addressId:Int?,
                    items:List<CartItem>){

        viewModelScope.launch(Dispatchers.IO) {
            val b= OrderRequestBody(
                project_id = projectId,
                customer_id =customerId,
                tax_value = taxValue,
                service_value = serviceValue,
                promo_code = promoCode,
                promo_code_discount_value =promoDiscount ,
                total_value=totalValue,
                city_id=cityId,
                delivery_type=deliveryType,
                address_id=addressId,
                items = items
            )
            val call: Call<OrderResponse> = ordersApi.store(b)
            val response= call.execute()
            withContext(Dispatchers.Main){
                try {
                    if (response.isSuccessful){
                        val body=response.body()
                        if (body!=null){
                            datum.value = UiState.Success(body)
                            orderStatus.intValue=ORDER_DONE
                        }else if(response.errorBody()!=null){
                            orderStatus.intValue=ORDER_NOT_COMPLETED

                            val errorBody= response.errorBody()!!.string()
                            //Log.e("HomeViewModel ErrorBody",errorBody)
                            Log.e("OrdersController Err01", "Error: Code-> ${response.code()} " +
                                    ",message-> $errorBody")
                            return@withContext
                        }
                    }else if(response.errorBody()!=null){
                        orderStatus.intValue=ORDER_NOT_COMPLETED
                        val errorBody= response.errorBody()!!.string()
                        //Log.e("HomeViewModel ErrorBody",errorBody)
                        Log.e("HomeViewModel Err01", "Error: Code-> ${response.code()} " +
                                ",message-> $errorBody")
                        return@withContext
                    }
                }
                catch (e:Exception){
                    orderStatus.intValue=ORDER_NOT_COMPLETED
                    e.printStackTrace()
                }
            }
            //if(networkAvailable){}else{withContext(Dispatchers.Main){orderCreate d.value= false}}
        }
    }

    fun show(projectId:Int,order: Order){

        viewModelScope.launch(Dispatchers.IO) {

            val call = ordersApi.show(projectId= projectId,customerId = order.customer_id!!, orderId = order.id!!)
            try {
                val response = call.execute()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            datum.value = UiState.Success(body)
                        } else if (response.errorBody() != null) {
                            val errorBody = response.errorBody()!!.string()
                            Log.e(
                                "OrdersController", "Err01 Index Error: Code-> ${response.code()} " +
                                        ",message-> $errorBody"
                            )
                            return@withContext
                        }
                    } else if (response.errorBody() != null) {
                        val errorBody = response.errorBody()!!.string()
                        //Log.e("HomeViewModel ErrorBody",errorBody)
                        Log.e(
                            "OrdersController", "Err02 Index Error: Code-> ${response.code()} " +
                                    ",message-> $errorBody"
                        )
                        return@withContext
                    }
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}