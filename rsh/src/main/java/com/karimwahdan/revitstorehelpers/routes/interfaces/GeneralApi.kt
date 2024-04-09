package com.karimwahdan.revitstorehelpers.routes.interfaces

import com.karimwahdan.revitstorehelpers.routes.prefixCustomer
import com.karimwahdan.rsm.basics.CityResponse
import com.karimwahdan.rsm.basics.UpdateItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GeneralApi {

    companion object{
        const val REFRESH_TOKEN="refresh_token"
        const val PAGE="page"
    }

    @GET("get-updates")
    fun getUpdates(): Call<UpdateItem>

    @GET("$prefixCustomer/countries")
    fun getCountries(
    )

    @GET("$prefixCustomer/cities")
    fun getCities(
        @Query("country_id") countryId:Int
    ):Call<CityResponse>

    @GET("$prefixCustomer/areas")
    fun getAreas(
        @Query("city_id") cityId:Int
    )
}