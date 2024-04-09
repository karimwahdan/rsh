@file:Suppress("PropertyName")

package com.karimwahdan.revitstorehelpers.routes.interfaces.requestBodies

import com.karimwahdan.rsm.cart.CartItem
import retrofit2.http.Body

data class OrderRequestBody(
    @Body val project_id: Int,
    @Body val customer_id:Int,
    @Body val city_id:Int?,
    @Body val tax_value:Float?,
    @Body val service_value:Float?,
    @Body val promo_code:String?,
    @Body val promo_code_discount_value:Float?,
    @Body val total_value:Float?,
    @Body val delivery_type:String?,
    @Body val address_id:Int?,
    @Body val items:List<CartItem>
)
