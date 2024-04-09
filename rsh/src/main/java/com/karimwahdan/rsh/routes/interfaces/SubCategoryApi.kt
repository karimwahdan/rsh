package com.karimwahdan.rsh.routes.interfaces

import com.karimwahdan.rsm.mixedResponses.SubCategoryAndProductResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SubCategoryApi {
    @GET("customer/sub-categories/index/by-category")
    fun index(
        @Query("project_id") project_id: Int,
        @Query("category_id") category_id: Int
    ): Call<SubCategoryAndProductResponse>

    @GET("customer/sub-categories/index/by-category")
    fun index(
        @Query("project_id") project_id: Int,
    ): Call<SubCategoryAndProductResponse>
}