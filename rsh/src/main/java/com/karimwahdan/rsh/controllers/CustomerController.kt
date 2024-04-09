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
import com.karimwahdan.rsm.customer.CustomerResponse
import com.karimwahdan.rsm.customer.DeliveryAddressesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call

class CustomerController: ViewModel() {
    val data =MutableLiveData<UiState<DeliveryAddressesResponse>> ()
    val state:LiveData<UiState<DeliveryAddressesResponse>> get()= data

    private val customer =MutableLiveData<UiState<CustomerResponse>> ()
    val customerState:LiveData<UiState<CustomerResponse>> get()= customer

    private val passwordResetResponse =MutableLiveData<UiState<String>> ()
    val passwordResetResponseState:LiveData<UiState<String>> get()= passwordResetResponse

    private val codeResponse =MutableLiveData<UiState<String>> ()
    val codeResponseState:LiveData<UiState<String>> get()= codeResponse

    private val api= Callers().customerApi()


    fun register(
        projectId:Int,
        firstName:String,
        middleName:String,
        lastName:String,
        username:String,
        email:String,
        password:String,
        mainMobile:String,
        countryId:Int
    ){

        viewModelScope.launch(Dispatchers.IO) {
            val call: Call<CustomerResponse> = api
                .register(
                    projectId=projectId,
                    first_name = firstName,
                    middle_name=middleName,
                    last_name=lastName,
                    username=username,
                    email=email,
                    password=password,
                    main_mobile=mainMobile,
                    country_id=countryId,
                )
            val response= call.execute()
            withContext(Dispatchers.Main){
                try {
                    if (response.isSuccessful){
                        val body=response.body()
                        if (body!=null){
                            customer.value = UiState.Success(body)
                        }else if(response.errorBody()!=null){
                            val errorBody= response.errorBody()!!.string()
                            val errorResponse= ErrorResponseParser().parseError(errorBody)
                            if (errorResponse!=null){
                                customer.value=UiState.Error(errorResponse)
                            }else{
                                customer.value=UiState.Error(ErrorResponse("Error Fetching Data",null))
                            }
                            return@withContext
                        }
                    }else if(response.errorBody()!=null){
                        val errorBody= response.errorBody()!!.string()
                        val errorResponse= ErrorResponseParser().parseError(errorBody)
                        if (errorResponse!=null){
                            customer.value=UiState.Error(errorResponse)
                        }else{
                            customer.value=UiState.Error(ErrorResponse("Error Fetching Data",null))
                        }
                        return@withContext
                    }
                }
                catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    fun login(projectId:Int,username:String,password:String,lang:String){

        viewModelScope.launch(Dispatchers.IO) {
            val call: Call<CustomerResponse> = api
                .login(
                    projectId=projectId,
                    username=username,
                    password=password,
                    lang = lang
                )
            val response= call.execute()
            withContext(Dispatchers.Main){
                try {
                    if (response.isSuccessful){
                        val body=response.body()
                        if (body!=null){
                            val c=body.data
                            if (c!=null){
                                customer.value = UiState.Success(body)
                            }else{
                                customer.value=UiState.Error(ErrorResponse("Error Fetching Data",null))
                            }

                        }else if(response.errorBody()!=null){
                            val errorBody= response.errorBody()!!.string()
                            val errorResponse= ErrorResponseParser().parseError(errorBody)
                            if (errorResponse!=null){
                                customer.value=UiState.Error(errorResponse)
                            }else{
                                customer.value=UiState.Error(ErrorResponse("Error Fetching Data",null))
                            }
                            return@withContext
                        }
                    }else if(response.errorBody()!=null){
                        val errorBody= response.errorBody()!!.string()
                        val errorResponse= ErrorResponseParser().parseError(errorBody)
                        if (errorResponse!=null){
                            customer.value=UiState.Error(errorResponse)
                        }else{
                            customer.value=UiState.Error(ErrorResponse("Error Fetching Data",null))
                        }
                        return@withContext
                    }
                }
                catch (e:Exception){
                    e.printStackTrace()
                }
            }


        }
    }

    fun sendEmail( projectId:Int,username:String
    ){
        viewModelScope.launch(Dispatchers.IO) {
            val call: Call<String> = api
                .requestPasswordResetCode(
                    projectId=projectId,
                    email=username)
            val response= call.execute()
            withContext(Dispatchers.Main){
                try {
                    if (response.isSuccessful){
                        val body=response.body()
                        if (body!=null){
                            if(body == "success"){
                                passwordResetResponse.value=UiState.Success(body)
                            }else{
                                passwordResetResponse.value=UiState.Error(ErrorResponse("Error Fetching Data",null))

                            }
                        }else if(response.errorBody()!=null){
                            val errorBody= response.errorBody()!!.string()
                            val errorResponse= ErrorResponseParser().parseError(errorBody)
                            if (errorResponse!=null){
                                passwordResetResponse.value=UiState.Error(errorResponse)
                            }else{
                                passwordResetResponse.value=UiState.Error(ErrorResponse("Error Fetching Data",null))
                            }
                            return@withContext
                        }
                    }else if(response.errorBody()!=null){
                        val errorBody= response.errorBody()!!.string()
                        val errorResponse= ErrorResponseParser().parseError(errorBody)
                        if (errorResponse!=null){
                            passwordResetResponse.value=UiState.Error(errorResponse)
                        }else{
                            passwordResetResponse.value=UiState.Error(ErrorResponse("Error Fetching Data",null))
                        }
                        return@withContext
                    }
                }
                catch (e:Exception){
                    e.printStackTrace()
                }
            }

        }
    }

    fun sendCode( projectId:Int,code:String,email: String
    ){
        viewModelScope.launch(Dispatchers.IO) {

            val call: Call<String> = api
                .checkPasswordResetCode(
                    projectId=projectId,
                    email=email,
                    code=code)
            val response= call.execute()
            withContext(Dispatchers.Main){
                try {
                    if (response.isSuccessful){
                        val body=response.body()
                        if (body!=null){
                            if(body == "success"){
                                codeResponse.value=UiState.Success(body)
                            }else{
                                codeResponse.value=UiState.Error(ErrorResponse("Error Fetching Data",null))

                            }
                        }else if(response.errorBody()!=null){
                            val errorBody= response.errorBody()!!.string()
                            val errorResponse= ErrorResponseParser().parseError(errorBody)
                            if (errorResponse!=null){
                                codeResponse.value=UiState.Error(errorResponse)
                            }else{
                                codeResponse.value=UiState.Error(ErrorResponse("Error Fetching Data",null))
                            }
                            return@withContext
                        }
                    }else if(response.errorBody()!=null){
                        val errorBody= response.errorBody()!!.string()
                        val errorResponse= ErrorResponseParser().parseError(errorBody)
                        if (errorResponse!=null){
                            codeResponse.value=UiState.Error(errorResponse)
                        }else{
                            codeResponse.value=UiState.Error(ErrorResponse("Error Fetching Data",null))
                        }
                        return@withContext
                    }
                }
                catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    fun resetPassword(projectId:Int, email: String,code: String,password:String
    ){

        viewModelScope.launch(Dispatchers.IO) {
            val call: Call<CustomerResponse> = api
                .passwordReset(
                    projectId=projectId,
                    email=email,
                    code=code,
                    password=password)
            val response= call.execute()
            withContext(Dispatchers.Main){
                try {
                    if (response.isSuccessful){
                        val body=response.body()
                        if (body!=null){
                            customer.value=UiState.Success(body)
                        }else if(response.errorBody()!=null){
                            val errorBody= response.errorBody()!!.string()
                            val errorResponse= ErrorResponseParser().parseError(errorBody)
                            if (errorResponse!=null){
                                customer.value=UiState.Error(errorResponse)
                            }else{
                                customer.value=UiState.Error(ErrorResponse("Error Fetching Data",null))
                            }
                            return@withContext
                        }
                    }else if(response.errorBody()!=null){
                        val errorBody= response.errorBody()!!.string()
                        val errorResponse= ErrorResponseParser().parseError(errorBody)
                        if (errorResponse!=null){
                            customer.value=UiState.Error(errorResponse)
                        }else{
                            customer.value=UiState.Error(ErrorResponse("Error Fetching Data",null))
                        }
                        return@withContext
                    }
                }
                catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    fun changePassword( projectId:Int,email: String,password:String
    ){

        viewModelScope.launch(Dispatchers.IO) {

            val call: Call<CustomerResponse> = api
                .passwordChange(
                    projectId=projectId,
                    email=email,
                    password=password)
            val response= call.execute()
            withContext(Dispatchers.Main){
                try {
                    if (response.isSuccessful){
                        val body=response.body()
                        if (body!=null){
                            customer.value=UiState.Success(body)
                        }else if(response.errorBody()!=null){
                            val errorBody= response.errorBody()!!.string()
                            val errorResponse= ErrorResponseParser().parseError(errorBody)
                            if (errorResponse!=null){
                                customer.value=UiState.Error(errorResponse)
                            }else{
                                customer.value=UiState.Error(ErrorResponse("Error Fetching Data",null))
                            }
                            return@withContext
                        }
                    }else if(response.errorBody()!=null){
                        val errorBody= response.errorBody()!!.string()
                        val errorResponse= ErrorResponseParser().parseError(errorBody)
                        if (errorResponse!=null){
                            customer.value=UiState.Error(errorResponse)
                        }else{
                            customer.value=UiState.Error(ErrorResponse("Error Fetching Data",null))
                        }
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