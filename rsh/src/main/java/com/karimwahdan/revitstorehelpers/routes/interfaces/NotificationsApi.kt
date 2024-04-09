package com.karimwahdan.revitstorehelpers.routes.interfaces

import com.karimwahdan.revitstorehelpers.routes.indexUrl
import com.karimwahdan.revitstorehelpers.routes.prefixCustomer
import com.karimwahdan.revitstorehelpers.routes.prefixNotifications
import com.karimwahdan.revitstorehelpers.routes.project_id
import com.karimwahdan.rsm.notifications.ProjectNotificationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NotificationsApi {
    @GET("$prefixCustomer/$prefixNotifications/$indexUrl")
    fun index(
        @Query(project_id) id: Int,
    ): Call<ProjectNotificationResponse>
}