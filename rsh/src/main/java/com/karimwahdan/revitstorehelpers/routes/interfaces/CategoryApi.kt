package com.karimwahdan.revitstorehelpers.routes.interfaces

import com.karimwahdan.revitstorehelpers.routes.indexUrl
import com.karimwahdan.revitstorehelpers.routes.prefixCategories
import com.karimwahdan.revitstorehelpers.routes.prefixCustomer
import com.karimwahdan.revitstorehelpers.routes.project_id
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