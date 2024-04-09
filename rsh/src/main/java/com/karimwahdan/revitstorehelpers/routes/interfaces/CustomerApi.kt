package com.karimwahdan.revitstorehelpers.routes.interfaces

import com.karimwahdan.revitstorehelpers.routes.prefixCustomer
import com.karimwahdan.revitstorehelpers.routes.project_id
import com.karimwahdan.rsm.customer.CustomerResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CustomerApi {

    @FormUrlEncoded
    @POST("$prefixCustomer/register")
    fun register(
        @Field(project_id) projectId: Int,
        @Field("first_name") first_name: String,
        @Field("middle_name") middle_name: String,
        @Field("last_name") last_name: String,
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("main_mobile") main_mobile: String,
        @Field("country_id") country_id: Int,
        ):Call<CustomerResponse>

    @GET("$prefixCustomer/login")
    fun login(
        @Query(project_id) projectId: Int,
        @Query("lang")lang:String="ar",
        @Query("username") username: String,
        @Query("password") password: String,
    ):Call<CustomerResponse>

    @GET("$prefixCustomer/password-reset-mail")
    fun requestPasswordResetCode(
        @Query(project_id) projectId: Int,
        @Query("username") email:String
    ):Call<String>

    @GET("$prefixCustomer/check-code")
    fun checkPasswordResetCode(
        @Query(project_id) projectId: Int,
        @Query("email") email: String,
        @Query("code") code:String
    ):Call<String>

    @GET("$prefixCustomer/reset-password")
    fun passwordReset(
        @Query(project_id) projectId: Int,
        @Query("code") code:String,
        @Query("email") email: String,
        @Query("password") password:String,
    ):Call<CustomerResponse>

    @GET("$prefixCustomer/change-password")
    fun passwordChange(
        @Query(project_id) projectId: Int,
        @Query("email") email: String,
        @Query("password") password:String,
    ):Call<CustomerResponse>
}