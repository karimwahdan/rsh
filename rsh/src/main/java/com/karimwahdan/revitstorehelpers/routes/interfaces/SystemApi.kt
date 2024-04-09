package com.karimwahdan.revitstorehelpers.routes.interfaces

import com.karimwahdan.revitstorehelpers.routes.prefixCustomer
import com.karimwahdan.rsm.basics.CityResponse
import com.karimwahdan.rsm.basics.CountryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SystemApi {
    @GET("$prefixCustomer/countries")
    fun indexCountries(
    ): Call<CountryResponse>

    @GET("$prefixCustomer/cities")
    fun indexCitiesByCountry(
        @Query("country_id")countryId:Int
    ): Call<CityResponse>
}