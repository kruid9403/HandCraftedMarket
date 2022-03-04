package com.handcraftedmarket.handcraftedmarket.service

import com.handcraftedmarket.handcraftedmarket.utils.coroutines.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers(Constants.CONTENT_TYPE_HEADER_VALUE)
    @GET
    fun getShippingEstimate(): Call<String>

//    var client: OkHttpClient = OkHttpClient.Builder()
//        .connectTimeout(30, TimeUnit.SECONDS)
//        .writeTimeout(30, TimeUnit.SECONDS) // write timeout
//        .readTimeout(30, TimeUnit.SECONDS)
//        .build()

    // retrofit call
//    var shipEngineRetrofit: Retrofit = Retrofit.Builder()
//        .baseUrl(SHIP_ENGINE_URL)
//        .client(client)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    var debugShipEngineRetrofit: Retrofit = Retrofit.Builder()
//        .baseUrl(SHIP_ENGINE_URL)
//        .client(client)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
}