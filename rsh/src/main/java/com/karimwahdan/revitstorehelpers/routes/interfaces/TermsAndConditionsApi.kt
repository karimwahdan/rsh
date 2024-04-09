package com.karimwahdan.revitstorehelpers.routes.interfaces

import com.karimwahdan.revitstorehelpers.routes.indexUrl
import com.karimwahdan.revitstorehelpers.routes.prefixCustomer
import com.karimwahdan.revitstorehelpers.routes.prefixTermsAndConditions
import com.karimwahdan.revitstorehelpers.routes.project_id
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