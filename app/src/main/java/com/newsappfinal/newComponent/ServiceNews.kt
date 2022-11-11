package com.newsappfinal.newComponent

import com.newsappfinal.model.Website
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceNews {
    @GET("v2/top-headlines")
    fun getsArticles(@Query("apiKey")key : String, @Query("country") country: String): Call<Website>
}