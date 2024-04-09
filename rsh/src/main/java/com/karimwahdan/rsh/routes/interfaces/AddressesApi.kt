package com.karimwahdan.rsh.routes.interfaces

import com.karimwahdan.rsh.routes.customer_id
import com.karimwahdan.rsh.routes.id
import com.karimwahdan.rsh.routes.indexUrl
import com.karimwahdan.rsh.routes.prefixAddresses
import com.karimwahdan.rsh.routes.prefixCustomer
import com.karimwahdan.rsh.routes.project_id
import com.karimwahdan.rsh.routes.storeUrl
import com.karimwahdan.rsm.customer.DeliveryAddressesResponse
import com.karimwahdan.rsm.customer.SingleDeliveryAddressResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

@Suppress("LocalVariableName")
interface AddressesApi {
    @GET("$prefixCustomer/$prefixAddresses/$indexUrl")
    fun index(
        @Query(project_id) projectId: Int,
        @Query(customer_id) customerId: Int ,
        ): Call<DeliveryAddressesResponse>

    @FormUrlEncoded
    @POST("$prefixCustomer/$prefixAddresses/$storeUrl")
    fun store(
        @Field(project_id) projectId: Int,
        @Field(customer_id) customerId: Int,
        @Field("name") name:String,
        @Field("mobile_no") mobileNo:String,
        @Field("alt_mobile_no") altMobileNo:String?,
        @Field("address") address:String,
        @Field("country_id") countryId:Int?,
        @Field("country_name") countryName:String,
        @Field("city_id") cityId:Int?,
        @Field("city_name") cityName:String,
        @Field("area_id") areaId:Int?,
        @Field("area_name") areaName:String,
        @Field("landmark") landmark:String,
        @Field("type") type:String,
        @Field("default") default:Int,
        @Field("longitude") longitude:Double?,
        @Field("latitude") latitude:Double?,

        ): Call<SingleDeliveryAddressResponse>

    @FormUrlEncoded
    @PUT("$prefixCustomer/$prefixAddresses/$indexUrl")
    fun update(
        @Query(id) ID: Int ,
        @Field("name") name:String,
        @Field("mobile_no") mobileNo:String,
        @Field("alt_mobile_no") altMobileNo:String?,
        @Field("address") address:String,
        @Field("country_id") countryId:Int?,
        @Field("country_name") countryName:String,
        @Field("city_id") cityId:Int?,
        @Field("city_name") cityName:String,
        @Field("area_id") areaId:Int?,
        @Field("area_name") areaName:String,
        @Field("landmark") landmark:String,
        @Field("type") type:String,
        @Field("default") default:String,
        @Field("longitude") longitude:Double?,
        @Field("latitude") latitude:Double?,
    ): Call<DeliveryAddressesResponse>

    @GET("$prefixCustomer/$prefixAddresses/$indexUrl")
    fun delete(@Query(id) ID: Int):Void
}