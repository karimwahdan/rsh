package com.karimwahdan.revitstorehelpers.routes.interfaces

import com.karimwahdan.revitstorehelpers.routes.OrderStatusValue
import com.karimwahdan.revitstorehelpers.routes.customer_id
import com.karimwahdan.revitstorehelpers.routes.indexUrl
import com.karimwahdan.revitstorehelpers.routes.prefixCustomer
import com.karimwahdan.revitstorehelpers.routes.prefixOrders
import com.karimwahdan.revitstorehelpers.routes.project_id
import com.karimwahdan.revitstorehelpers.routes.storeUrl
import com.karimwahdan.rsm.deliveryCharges.SingleDeliveryChargeResponse
import com.karimwahdan.rsm.orders.AllOrdersResponse
import com.karimwahdan.rsm.orders.OrderResponse
import com.karimwahdan.revitstorehelpers.routes.interfaces.requestBodies.OrderRequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OrdersApi {
    @GET("$prefixCustomer/delivery-charges/$indexUrl/city")
    fun cityDeliveryCharge(
        @Query(project_id)projectId: Int,
        @Query("city_id")cityId:Int
    ):Call<SingleDeliveryChargeResponse>

    @GET("$prefixCustomer/$prefixOrders/$indexUrl")
    fun index(
        @Query(project_id) projectId: Int,
        @Query(customer_id)customerId: Int
    ):Call<AllOrdersResponse>

    @GET("$prefixCustomer/$prefixOrders/view")
    fun show(
        @Query(project_id) projectId: Int,
        @Query(customer_id)customerId: Int,
        @Query("id")orderId: Int

    ):Call<OrderResponse>

    @POST("$prefixCustomer/$prefixOrders/$storeUrl")
    fun store(@Body orderRequestBody: OrderRequestBody): Call<OrderResponse>

    @GET("$prefixCustomer/$prefixOrders/$indexUrl-status")
    fun indexByStatus(
        @Query(project_id) projectId: Int,
        @Query(customer_id) customerId:Int,
        @Query("order_status_id") statusId:Int= OrderStatusValue.inProgress,
    )
}