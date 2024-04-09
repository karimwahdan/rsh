package com.karimwahdan.revitstorehelpers.routes.interfaces

import com.karimwahdan.rsm.home.HomeDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApi {
    @GET("customer/home/{project_id}")
    fun home(@Path("project_id") project_id:Int): Call<HomeDataModel>
}