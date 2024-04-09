package com.karimwahdan.rsh.routes.interfaces


import com.karimwahdan.rsh.routes.customer_id
import com.karimwahdan.rsh.routes.prefixCustomer
import com.karimwahdan.rsh.routes.project_id
import com.karimwahdan.rsm.wishlists.WishItemResponse
import com.karimwahdan.rsm.wishlists.WishlistResponse
import com.karimwahdan.rsh.routes.interfaces.requestBodies.WishlistMultipleRequestBody
import com.karimwahdan.rsh.routes.interfaces.requestBodies.WishlistSingleRequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WishlistApi {
    @GET("$prefixCustomer/wishlist/index")
    fun index(
        @Query(project_id) projectId: Int,
        @Query(customer_id) customerId:Int,
    ): Call<WishlistResponse>

    @POST("$prefixCustomer/wishlist/store")
    fun store(@Body wishlistRequestBody: WishlistSingleRequestBody): Call<WishItemResponse>

    @POST("$prefixCustomer/wishlist/store-all")
    fun storeAll(@Body wishlistRequestBody: WishlistMultipleRequestBody): Call<WishlistResponse>

    @GET("$prefixCustomer/wishlist/delete")
    fun delete(
        @Query(project_id) projectId: Int,
        @Query(customer_id) customerId:Int,
        @Query("product_id")productId:Int,
    ): Call<Int>

    @GET("$prefixCustomer/wishlist/delete-all")
    fun deleteAll(
        @Query("project_id") project_id: Int,
        @Query("customer_id")customer_id:Int,
    ): Call<Void>
}