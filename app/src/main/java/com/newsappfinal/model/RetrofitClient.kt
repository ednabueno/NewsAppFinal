package com.newsappfinal.model

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var retrofit: Retrofit? = null
    fun getClient(baseUrl: String):Retrofit{

//        val httpClient = OkHttpClient.Builder()
//            .callTimeout(2, TimeUnit.SECONDS)
//            .connectTimeout(20, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)

        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(httpClient.build())
                .build()
        }
        return retrofit!!
    }
}