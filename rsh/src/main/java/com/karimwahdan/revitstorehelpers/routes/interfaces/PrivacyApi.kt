package com.karimwahdan.revitstorehelpers.routes.interfaces

import com.karimwahdan.revitstorehelpers.routes.indexUrl
import com.karimwahdan.revitstorehelpers.routes.prefixCustomer
import com.karimwahdan.revitstorehelpers.routes.prefixPrivacy
import com.karimwahdan.revitstorehelpers.routes.project_id
import com.karimwahdan.rsm.privacyPolicy.PrivacyPolicyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PrivacyApi {
    @GET("$prefixCustomer/$prefixPrivacy/$indexUrl")
    fun index(
        @Query(project_id) id: Int,
    ): Call<PrivacyPolicyResponse>
}