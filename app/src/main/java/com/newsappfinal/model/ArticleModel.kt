package com.newsappfinal.model

import android.annotation.SuppressLint
import android.util.Log.d
import android.util.Log.i
import com.newsappfinal.newComponent.NewsComponent
import com.newsappfinal.newComponent.ServiceNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArticleModel: NewsComponent.Model
{
    override suspend fun getNewsFromApi(apiKey: String): ArrayList<ArticleData>
    {
        var newsArticles:ArrayList<ArticleData> = arrayListOf()
//        newsArticles = arrayListOf(ArticleData("a", "b", "c", "https://ph.usembassy.gov/u-s-convenes-anti-drug-abuse-coalitions-to-strengthen-philippine-drug-use-prevention-strategies/", "e", "f", "g"))
            try {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Common.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val retrofitApi: ServiceNews = retrofit.create(ServiceNews::class.java)

                val call = retrofitApi.getsArticles(apiKey, "ph")

                call.enqueue(object : Callback<Website> {
                    override fun onResponse(call: Call<Website>, response: Response<Website>) {
                        val articles = response.body()
                        i("articleResponse", articles.toString())
                        val article: ArrayList<ArticleData> = articles!!.arrayListOfArticles
                        article.forEach(){
                            newsArticles?.add(it)
                        }
                    }

                    override fun onFailure(call: Call<Website?>, t: Throwable) {
                        d("Article fetching", "Failed to Fetch")

                    }
                })

            } catch (e: Exception) {
                e.printStackTrace()
                d("Article Fetching...", e.message.toString())
            }
        i("outTryNewsArticle", newsArticles?.size.toString())
        return newsArticles
        //i("outTryNewsArticles", newsArticles?.size.toString())
        //return newsArticles
    }
}