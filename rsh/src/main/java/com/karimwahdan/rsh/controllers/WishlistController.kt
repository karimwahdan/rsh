@file:Suppress("unused")

package com.karimwahdan.rsh.controllers

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karimwahdan.rsh.app.UiState
import com.karimwahdan.rsh.routes.Callers
import com.karimwahdan.rsh.routes.interfaces.requestBodies.WishlistSingleRequestBody
import com.karimwahdan.rsm.ErrorResponse
import com.karimwahdan.rsm.wishlists.WishItemResponse
import com.karimwahdan.rsm.wishlists.WishlistResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call

class WishlistController: ViewModel() {
    private val wishlistData = MutableLiveData<UiState<WishlistResponse>>()
    val wishlistState: LiveData<UiState<WishlistResponse>> get() = wishlistData

    private val wishlistDatum = MutableLiveData<UiState<WishItemResponse>> ()
    val wishlistSingleState: LiveData<UiState<WishItemResponse>> get()= wishlistDatum

    private val wishListStoreDatum = MutableLiveData<UiState<Int>> ()
    val wishlistSingleStoreState: LiveData<UiState<Int>> get()= wishListStoreDatum
    private val wishlistApi= Callers().wishlistsApi()
    @Suppress("MemberVisibilityCanBePrivate")
    val wishlistSaved= mutableStateOf(false)

    fun index(projectId:Int, customerId: Int?) {

        viewModelScope.launch(Dispatchers.IO) {
           if (customerId!=null){
               val call = wishlistApi.index(projectId= projectId,customerId = customerId)
               val response = call.execute()
               withContext(Dispatchers.Main) {
                   try {
                       if (response.isSuccessful) {
                           val body = response.body()
                           if (body != null) {
                               wishlistData.value = UiState.Success(body)
                           } else if (response.errorBody() != null) {
                               val errorBody = response.errorBody()!!.string()
                               Log.e(
                                   "wishlistController", "Err01 Index Error: Code-> ${response.code()} " +
                                           ",message-> $errorBody"
                               )
                               return@withContext
                           }
                       } else if (response.errorBody() != null) {
                           val errorBody = response.errorBody()!!.string()
                           //Log.e("HomeViewModel ErrorBody",errorBody)
                           Log.e(
                               "wishlistController", "Err02 Index Error: Code-> ${response.code()} " +
                                       ",message-> $errorBody"
                           )
                           return@withContext
                       }
                   } catch (e: Exception) {
                       e.printStackTrace()
                   }
               }
           }else{
               withContext(Dispatchers.Main){wishlistData.value= UiState.Error(ErrorResponse("No Data",null))}
           }
            //if(networkAvailable){}else{withContext(Dispatchers.Main){wishlistData.value= noInternetState}}
        }
    }
    fun storeWishlistItem(projectId:Int, customerId:Int,
                          productId:Int){

        viewModelScope.launch(Dispatchers.IO) {
            val b= WishlistSingleRequestBody(
                project_id = projectId,
                customer_id =customerId,
                product_id = productId,

            )
            val call: Call<WishItemResponse> = wishlistApi.store(b)
            val response= call.execute()
            withContext(Dispatchers.Main){
                try {
                    if (response.isSuccessful){
                        val body=response.body()
                        if (body!=null){
                            wishlistDatum.value = UiState.Success(body)
                            wishlistSaved.value=true
                        }else if(response.errorBody()!=null){
                            val errorBody= response.errorBody()!!.string()
                            //Log.e("HomeViewModel ErrorBody",errorBody)
                            Log.e("OrdersController Err01", "Error: Code-> ${response.code()} " +
                                    ",message-> $errorBody")
                            return@withContext
                        }
                    }else if(response.errorBody()!=null){
                        val errorBody= response.errorBody()!!.string()
                        //Log.e("HomeViewModel ErrorBody",errorBody)
                        Log.e("HomeViewModel Err01", "Error: Code-> ${response.code()} " +
                                ",message-> $errorBody")
                        return@withContext
                    }
                }
                catch (e:Exception){
                    e.printStackTrace()
                }
            }
            //if(networkAvailable){}else{withContext(Dispatchers.Main){wishlistSaved.value= false}}
        }
    }

    fun deleteWishlistItem(projectId:Int, customerId:Int,
                           productId:Int){
        viewModelScope.launch(Dispatchers.IO) {
            val call: Call<Int> = wishlistApi.delete(projectId = projectId,
                customerId=customerId, productId = productId)
            val response= call.execute()
            withContext(Dispatchers.Main){
                try {
                    if (response.isSuccessful){
                        val body=response.body()
                        if (body!=null){
                            wishListStoreDatum.value = UiState.Success(body)
                            wishlistSaved.value=true
                        }else if(response.errorBody()!=null){
                            val errorBody= response.errorBody()!!.string()
                            //Log.e("HomeViewModel ErrorBody",errorBody)
                            Log.e("OrdersController Err01", "Error: Code-> ${response.code()} " +
                                    ",message-> $errorBody")
                            return@withContext
                        }
                    }else if(response.errorBody()!=null){
                        val errorBody= response.errorBody()!!.string()
                        //Log.e("HomeViewModel ErrorBody",errorBody)
                        Log.e("HomeViewModel Err01", "Error: Code-> ${response.code()} " +
                                ",message-> $errorBody")
                        return@withContext
                    }
                }
                catch (e:Exception){
                    e.printStackTrace()
                }
            }
            //if(networkAvailable){}else{withContext(Dispatchers.Main){wishlistSaved.value= false}}
        }
    }
}