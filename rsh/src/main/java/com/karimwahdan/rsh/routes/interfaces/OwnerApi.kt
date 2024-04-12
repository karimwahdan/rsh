package com.karimwahdan.rsh.routes.interfaces

import com.karimwahdan.rsh.routes.prefixOwner
import com.karimwahdan.rsm.owner.OwnerResponse
import com.karimwahdan.rsm.project.ProjectResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OwnerApi {
    @GET("$prefixOwner/login")
    fun login(
        @Query("email") username: String,
        @Query("password") password:String,
    ): Call<OwnerResponse>

    @GET("$prefixOwner/project/login")
    fun loginProject(
        @Query("project_owner_id") ownerId: Int,
        @Query("project_unique_code") code:String,
    ): Call<ProjectResponse>
}