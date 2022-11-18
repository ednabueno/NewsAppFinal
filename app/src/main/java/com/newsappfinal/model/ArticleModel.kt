package com.newsappfinal.model

import android.util.Log.e
import android.util.Log.i
import com.newsappfinal.newComponent.NewsComponent
import com.newsappfinal.newComponent.ServiceNews
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleModel: NewsComponent.Model
{
    override fun getNewsArticles(onFinishedListener: NewsComponent.Model.OnFinishedListener) {
        var serviceNews:ServiceNews = RetrofitClient.getClient(Common.BASE_URL).create(ServiceNews::class.java)

        val call = serviceNews.getsArticles(Common.API_KEY, "ph")
        call.enqueue(object : Callback<Website>{
            override fun onResponse(call: Call<Website>, response: Response<Website>) {
                val articles:ArrayList<ArticleData>? = response.body()?.arrayListOfArticles
                i("getNewsArticles", articles!!.size.toString())
                onFinishedListener.onFinished(articles)
            }

            override fun onFailure(call: Call<Website>, t: Throwable) {
                e("getNewsArticles", t.toString())
                onFinishedListener.onFailure(t)
            }

        })
    }
}