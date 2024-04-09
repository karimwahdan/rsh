package com.karimwahdan.revitstorehelpers.routes.interfaces

import com.karimwahdan.revitstorehelpers.routes.indexUrl
import com.karimwahdan.revitstorehelpers.routes.prefixAboutUs
import com.karimwahdan.revitstorehelpers.routes.prefixCustomer
import com.karimwahdan.revitstorehelpers.routes.project_id
import com.karimwahdan.rsm.aboutUs.AboutUsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AboutUsApi {
    @GET("$prefixCustomer/$prefixAboutUs/$indexUrl")
    fun index(
        @Query(project_id) id: Int,
    ): Call<AboutUsResponse>
}