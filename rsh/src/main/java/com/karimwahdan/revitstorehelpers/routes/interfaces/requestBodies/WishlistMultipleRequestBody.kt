@file:Suppress("PropertyName")

package com.karimwahdan.revitstorehelpers.routes.interfaces.requestBodies

import retrofit2.http.Body

data class WishlistMultipleRequestBody(
    @Body val project_id: Int,
    @Body val customer_id:Int,
    @Body val product_ids:List<Int>
)
