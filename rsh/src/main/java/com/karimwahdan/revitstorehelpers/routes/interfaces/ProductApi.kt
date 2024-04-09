package com.karimwahdan.revitstorehelpers.routes.interfaces

import com.karimwahdan.revitstorehelpers.routes.indexUrl
import com.karimwahdan.revitstorehelpers.routes.prefixCustomer
import com.karimwahdan.revitstorehelpers.routes.prefixProducts
import com.karimwahdan.revitstorehelpers.routes.viewUrl
import com.karimwahdan.rsm.product.ProductResponse
import com.karimwahdan.rsm.product.ProductSingleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

    @GET("$prefixCustomer/$prefixProducts/$viewUrl")
    fun view(
        @Query("project_id") project_id: Int,
        @Query("id") product_id: Int
    ): Call<ProductSingleResponse>


    @GET("$prefixCustomer/$prefixProducts/$indexUrl/by-category")
    fun indexByCategory(
        @Query("project_id") project_id: Int,
        @Query("category_id") category_id: Int
    ): Call<ProductResponse>

    @GET("$prefixCustomer/$prefixProducts/$indexUrl/by-sub-category")
    fun indexBySubCategory(
        @Query("project_id") project_id: Int,
        @Query("sub_category_id") subCategory_id: Int
    ): Call<ProductResponse>
    @GET("$prefixCustomer/$prefixProducts/$indexUrl")
    fun index(@Query("project_id") project_id: Int): Call<ProductResponse>

    @GET("$prefixCustomer/$prefixProducts/search")
    fun search(@Query("project_id") project_id:Int,
               @Query("name") name:String): Call<ProductResponse>

    @GET("$prefixCustomer/$prefixProducts/featured")
    fun featured(@Query("project_id") project_id:Int,
                 @Query("id") id:Int): Call<ProductResponse>

}