package com.karimwahdan.rsh.routes.interfaces

import com.karimwahdan.rsh.routes.indexUrl
import com.karimwahdan.rsh.routes.prefixCategories
import com.karimwahdan.rsh.routes.prefixCustomer
import com.karimwahdan.rsh.routes.project_id
import com.karimwahdan.rsm.categories.CategoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryApi {
    @GET("$prefixCustomer/$prefixCategories/$indexUrl")
    fun index(
        @Query(project_id) id: Int ,
    ): Call<CategoryResponse>
}