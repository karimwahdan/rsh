@file:Suppress("PropertyName")

package com.karimwahdan.rsh.routes.interfaces.requestBodies

import retrofit2.http.Body

data class WishlistSingleRequestBody(
    @Body val project_id: Int,
    @Body val customer_id:Int,
    @Body val product_id:Int
)
