package com.newsappfinal.model

import android.annotation.SuppressLint
import android.util.Log.*
import com.newsappfinal.newComponent.NewsComponent
import com.newsappfinal.newComponent.ServiceNews
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArticleModel: NewsComponent.Model
{
    /*
    override suspend fun getNewsFromApi(apiKey: String): ArrayList<ArticleData>
    {

        val coroutine = CoroutineScope(Dispatchers.IO).async {
//        newsArticles = arrayListOf(ArticleData("a", "b", "c", "https://ph.usembassy.gov/u-s-convenes-anti-drug-abuse-coalitions-to-strengthen-philippine-drug-use-prevention-strategies/", "e", "f", "g"))
            var newsArticles:ArrayList<ArticleData> = arrayListOf()
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
                        article.forEach() {
                            newsArticles?.add(it)
                        }
                        i("enqueue", newsArticles.size.toString())
                    }

                    override fun onFailure(call: Call<Website?>, t: Throwable) {
                        d("Article fetching", "Failed to Fetch")

                    }
                })

            } catch (e: Exception) {
                e.printStackTrace()
                d("Article Fetching...", e.message.toString())
            }
            i("coroutineModel", newsArticles.size.toString())
            newsArticles
        }
        return coroutineScope {
                runBlocking {
                    withContext(Dispatchers.Default) {
                    val a = coroutine.await()
                    i("articleModel", a.size.toString())
                    a
                }
            }
//            withContext(Dispatchers.Default){
//                val a = coroutine.await()
//                i("articleModel", a.size.toString())
//                a
//            }
        }
//        i("outTryNewsArticle", newsArticles?.size.toString())
//        return newsArticles
        //i("outTryNewsArticles", newsArticles?.size.toString())
        //return newsArticles
    }

     */
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