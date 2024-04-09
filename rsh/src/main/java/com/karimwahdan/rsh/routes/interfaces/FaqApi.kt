package com.karimwahdan.rsh.routes.interfaces

import com.karimwahdan.rsh.routes.indexUrl
import com.karimwahdan.rsh.routes.prefixCustomer
import com.karimwahdan.rsh.routes.prefixFaq
import com.karimwahdan.rsh.routes.project_id
import com.karimwahdan.rsm.faq.FAQResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FaqApi {
    @GET("$prefixCustomer/$prefixFaq/$indexUrl")
    fun index(@Query(project_id) id: Int,): Call<FAQResponse>
}