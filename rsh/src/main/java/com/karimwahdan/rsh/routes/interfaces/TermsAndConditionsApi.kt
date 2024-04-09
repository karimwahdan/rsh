package com.karimwahdan.rsh.routes.interfaces

import com.karimwahdan.rsh.routes.indexUrl
import com.karimwahdan.rsh.routes.prefixCustomer
import com.karimwahdan.rsh.routes.prefixTermsAndConditions
import com.karimwahdan.rsh.routes.project_id
import com.karimwahdan.rsm.termsAndConditions.TermsAndConditionsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TermsAndConditionsApi {
    @GET("$prefixCustomer/$prefixTermsAndConditions/$indexUrl")
    fun index(
        @Query(project_id) id: Int,
    ): Call<TermsAndConditionsResponse>
}