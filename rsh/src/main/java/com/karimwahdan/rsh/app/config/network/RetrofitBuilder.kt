package com.karimwahdan.rsh.app.config.network

import com.facebook.stetho.okhttp3.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.karimwahdan.rsh.routes.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {
    private val client: OkHttpClient = buildClient()
    private val retrofit = buildRetrofit()
    private val connectException = MutableStateFlow(false)

    private fun resetConnectException() {
        connectException.value = false
    }

    private fun setConnectException() {
        connectException.value = true
    }

    private fun buildClient(): OkHttpClient {
        //val logging =HttpLoggingInterceptor();
        //logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        var request:Request
        val builder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                resetConnectException()
                val x= chain.request().newBuilder()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Accept", "application/json")
                    .addHeader("Connection", "close")
                    .build()
                request = x
                val connection=chain.connection()
                try{
                    if (connection!=null){
                        if (connection.socket().isConnected){
                            chain.proceed(request)
                        }
                        else{
                            setConnectException()
                            chain.proceed(request)
                        }
                    }
                    else{
                        setConnectException()
                        chain.proceed(request)
                    }
                }catch (e:Exception){
                    setConnectException()
                    e.printStackTrace()
                    chain.proceed(request)
                }

            }
        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(StethoInterceptor())
        }
        return builder.build()
    }

    private fun buildRetrofit(): Retrofit {
        val factory=KotlinJsonAdapterFactory()
        val moshi = Moshi.Builder().add(factory).build()
        val cf=MoshiConverterFactory.create(moshi)
        val erf=ErrorResponseFactory.create(moshi)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client).addConverterFactory(cf).addConverterFactory(erf).build()
    }

    fun <T> createService(service: Class<T>): T {return retrofit.create(service)}
}