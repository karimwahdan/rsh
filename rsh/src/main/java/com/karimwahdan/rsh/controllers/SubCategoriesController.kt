@file:Suppress("unused")

package com.karimwahdan.rsh.controllers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karimwahdan.rsh.app.UiState
import com.karimwahdan.rsh.routes.Callers
import com.karimwahdan.rsm.categories.Category
import com.karimwahdan.rsm.mixedResponses.SubCategoryAndProductResponse

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call

class SubCategoriesController: ViewModel() {
    val data = MutableLiveData<UiState<SubCategoryAndProductResponse>>()
    val state:LiveData<UiState<SubCategoryAndProductResponse>> get() = data
    private val api= Callers().subCategoryApi()

    fun indexByCategory(projectId:Int,category: Category){

        viewModelScope.launch(Dispatchers.IO){
            val call: Call<SubCategoryAndProductResponse> = api.index(project_id = projectId,category_id = category.id!!)
            val response=call.execute()
            withContext(Dispatchers.Main){
                try {
                    if (response.isSuccessful){
                        val body=response.body()
                        if (body!=null){
                            data.value = UiState.Success(body)

                        }else if(response.errorBody()!=null){
                            val errorBody= response.errorBody()!!.string()
                            //Log.e("HomeViewModel ErrorBody",errorBody)
                            Log.e("SubCategoriesController",
                                "Err01 Error: Code-> ${response.code()} " +
                                        ",message-> $errorBody")
                            return@withContext
                        }
                    }else if(response.errorBody()!=null){
                        val errorBody= response.errorBody()!!.string()
                        //Log.e("HomeViewModel ErrorBody",errorBody)
                        Log.e("SubCategoriesController", "Err02 1Error: Code-> ${response.code()} " +
                                ",message-> $errorBody")
                        return@withContext
                    }
                }
                catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }

    }
}