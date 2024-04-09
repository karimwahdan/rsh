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
import com.karimwahdan.rsm.product.ProductResponse
import com.karimwahdan.rsm.product.ProductSingleResponse
import com.karimwahdan.rsm.wishlists.WishItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call

class ProductController : ViewModel() {

    private val data = MutableLiveData<UiState<ProductSingleResponse>>()
    val state: LiveData<UiState<ProductSingleResponse>> get() = data

    private val list = MutableLiveData<UiState<ProductResponse>>()
    val listState: LiveData<UiState<ProductResponse>> get() = list
    val api = Callers().productApi()

    private val wishlistDatum = MutableLiveData<UiState<WishItemResponse>> ()
    val wishlistSingleState: LiveData<UiState<WishItemResponse>> get()= wishlistDatum

    private val wishListStoreDatum = MutableLiveData<UiState<Int>> ()
    val wishlistSingleStoreState: LiveData<UiState<Int>> get()= wishListStoreDatum
    private val wishlistApi=Callers().wishlistsApi()

    @Suppress("MemberVisibilityCanBePrivate")
    val wishlistSaved= mutableStateOf(false)

    fun view(projectId:Int,productId: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            val call = api.view(project_id=projectId,product_id = productId)
            val response = call.execute()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            data.value = UiState.Success(body)

                        } else if (response.errorBody() != null) {
                            val errorBody = response.errorBody()!!.string()
                            //Log.e("HomeViewModel ErrorBody",errorBody)
                            Log.e(
                                "ProductController", "Err01 Error: Code-> ${response.code()} " +
                                        ",message-> $errorBody"
                            )
                            return@withContext
                        }
                    } else if (response.errorBody() != null) {
                        val errorBody = response.errorBody()!!.string()
                        //Log.e("HomeViewModel ErrorBody",errorBody)
                        Log.e(
                            "ProductController", "Err02 Error: Code-> ${response.code()} " +
                                    ",message-> $errorBody"
                        )
                        return@withContext
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun indexByCategory(projectId:Int, categoryId: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            val call = api.indexByCategory(project_id = projectId,category_id = categoryId)
            val response = call.execute()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            list.value = UiState.Success(body)

                        } else if (response.errorBody() != null) {
                            val errorBody = response.errorBody()!!.string()
                            //Log.e("HomeViewModel ErrorBody",errorBody)
                            Log.e(
                                "HomeViewModel Err01", "Error: Code-> ${response.code()} " +
                                        ",message-> $errorBody"
                            )
                            return@withContext
                        }
                    } else if (response.errorBody() != null) {
                        val errorBody = response.errorBody()!!.string()
                        //Log.e("HomeViewModel ErrorBody",errorBody)
                        Log.e(
                            "HomeViewModel Err01", "Error: Code-> ${response.code()} " +
                                    ",message-> $errorBody"
                        )
                        return@withContext
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            /*
            if(networkAvailable){

            }else {
                withContext(Dispatchers.Main) {
                    data.value = noInternetState
                }
            }
             */
        }
    }

    fun indexBySubCategory(projectId:Int, subCategoryId: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            val call = api.indexBySubCategory(project_id = projectId,subCategory_id = subCategoryId)
            val response = call.execute()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            list.value = UiState.Success(body)

                        } else if (response.errorBody() != null) {
                            val errorBody = response.errorBody()!!.string()
                            //Log.e("HomeViewModel ErrorBody",errorBody)
                            Log.e(
                                "HomeViewModel Err01", "Error: Code-> ${response.code()} " +
                                        ",message-> $errorBody"
                            )
                            return@withContext
                        }
                    } else if (response.errorBody() != null) {
                        val errorBody = response.errorBody()!!.string()
                        //Log.e("HomeViewModel ErrorBody",errorBody)
                        Log.e(
                            "HomeViewModel Err01", "Error: Code-> ${response.code()} " +
                                    ",message-> $errorBody"
                        )
                        return@withContext
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

          /*
            if(networkAvailable){

            } else {
                withContext(Dispatchers.Main) {
                    list.value = noInternetState
                }
            }
           */

        }
    }

    fun index(projectId:Int) {

        viewModelScope.launch(Dispatchers.IO) {
            val call = api.index(projectId)
            val response = call.execute()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            list.value = UiState.Success(body)

                        } else if (response.errorBody() != null) {
                            val errorBody = response.errorBody()!!.string()
                            //Log.e("HomeViewModel ErrorBody",errorBody)
                            Log.e(
                                "HomeViewModel Err01", "Error: Code-> ${response.code()} " +
                                        ",message-> $errorBody"
                            )
                            return@withContext
                        }
                    } else if (response.errorBody() != null) {
                        val errorBody = response.errorBody()!!.string()
                        //Log.e("HomeViewModel ErrorBody",errorBody)
                        Log.e(
                            "HomeViewModel Err01", "Error: Code-> ${response.code()} " +
                                    ",message-> $errorBody"
                        )
                        return@withContext
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            /*
            if(networkAvailable){

            }else {
                withContext(Dispatchers.Main) {
                    list.value = noInternetState
                }
            }
             */

        }
    }

    fun indexFeatured(projectId:Int, featuredId: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            val call = api.featured(project_id = projectId,id = featuredId)
            val response = call.execute()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            list.value = UiState.Success(body)

                        } else if (response.errorBody() != null) {
                            val errorBody = response.errorBody()!!.string()
                            //Log.e("HomeViewModel ErrorBody",errorBody)
                            Log.e(
                                "HomeViewModel Err01", "Error: Code-> ${response.code()} " +
                                        ",message-> $errorBody"
                            )
                            return@withContext
                        }
                    } else if (response.errorBody() != null) {
                        val errorBody = response.errorBody()!!.string()
                        //Log.e("HomeViewModel ErrorBody",errorBody)
                        Log.e(
                            "HomeViewModel Err01", "Error: Code-> ${response.code()} " +
                                    ",message-> $errorBody"
                        )
                        return@withContext
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            /*
             if(networkAvailable){

             }else {
                 withContext(Dispatchers.Main) {
                     list.value = noInternetState
                 }
             }
             */
        }
    }
    fun storeWishlistItem(projectId:Int,
                          customerId:Int,
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
            /*
            if(networkAvailable){

            }
            else{
                withContext(Dispatchers.Main){
                    wishlistSaved.value= false
                }
            }
            */
        }
    }

    @Suppress("unused")
    fun deleteWishlistItem(projectId:Int,
                           customerId:Int,
                           productId:Int){

        viewModelScope.launch(Dispatchers.IO) {
            val call: Call<Int> = wishlistApi.delete(
                projectId = projectId,
                customerId=customerId,
                productId = productId)
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

            /*
             if(networkAvailable){}
             else{withContext(Dispatchers.Main){wishlistSaved.value= false}}
             */
            }
        }
    }
