package com.karimwahdan.rsh.routes.interfaces

import com.karimwahdan.rsh.routes.indexUrl
import com.karimwahdan.rsh.routes.prefixAboutUs
import com.karimwahdan.rsh.routes.prefixCustomer
import com.karimwahdan.rsh.routes.project_id
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