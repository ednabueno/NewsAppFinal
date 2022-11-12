package com.newsappfinal.model

import android.content.Context
import android.util.Log.d
import android.util.Log.i
import com.newsappfinal.newComponent.NewsComponent
import com.newsappfinal.newComponent.ServiceNews
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ArticleModel: NewsComponent.Model
{
    override fun getNewsFromApi(apiKey: String, mContext: Context): ArrayList<ArticleData>?
    {
        var newsArticles: ArrayList<ArticleData>? = null
        //var mService:NewsComponent.Model.NewsService
        /* making request to the FAST Android Networking Library
        returns JSON object containing news articles from the news api or it will return error
         */
        newsArticles = arrayListOf(ArticleData("a", "b", "c", "https://ph.usembassy.gov/u-s-convenes-anti-drug-abuse-coalitions-to-strengthen-philippine-drug-use-prevention-strategies/", "e", "f", "g"))
        try {
            /*
            ako nasusuya saimu retrofit, pati ika kotlin na dadamay kana, feeling ko nasusulsulan ka
            lang ni retrofit!
            Kala garu nindu uurungan ko kamu!
            si DS ani na dakulon requirements dae ko tgsukuan, kamu pa kaya!
            Kung abu nindu saku, mayo kamu magigibo dahil gusto ko kamu maging parti kang buhay ko. ayiiee <3
            :)
             */
            val retrofit = Retrofit.Builder()
                .baseUrl(Common.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
               // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            val retrofitApi : ServiceNews = retrofit.create(ServiceNews::class.java)

            val call = retrofitApi.getsArticles(apiKey, "ph")

            i("article", call.toString())

            call.enqueue(object : Callback<Website> {
                override fun onResponse(call: Call<Website>, response: Response<Website>) {
                    val articles = response.body()
                    if(articles != null){
                        val article :ArrayList<ArticleData> = articles.arrayListOfArticles
                        newsArticles!!.clear()
                        article!!.forEach(){
                            newsArticles!!.add(it)
                        }
                    }
                }

                override fun onFailure(call: Call<Website?>, t: Throwable) {
                    d("Article fetching", "Failed to Fetch")
                }
            })
            /*
                        mService = Common.newsService
            mService.sources.enqueue(object : Callback<Website>{
                override fun onResponse(call: Call<Website>, response: Response<Website>)
                {
                    newsArticles!!.clear()

                    if (response.body()?.status.equals("ok")){
                        var articleData = response.body()!!.articleData
                        newsArticles = articleData as ArrayList<ArticleData>?
                    }
                    else
                    {
                        d("Article Fetching", "Failed Fetching Articles")
                    }

//                    }
                }

                override fun onFailure(call: Call<Website>, t: Throwable) {
                    d("Article Fetching", "Failed Fetching Articles")
                }
            })

            looping through all the articles to access them individually
            newsArticles?.clear()
            for (i in 0 until articles.length()) {
                val article: JSONObject = articles.getJSONObject(i)
                var author: String = article.getString("author")
                val title: String = article.getString("title")
                val description: String = article.getString("description")
                val url: String = article.getString("url")
                val urlToImage: String = article.getString("urlToImage")
                val publishedAt: String = article.getString("publishedAt")
                val content: String = article.getString("content")
                if (author.isEmpty() || author.equals("null"))
                {
                    author = ""
                }
                currentArticle?.author = author
                currentArticle?.title = title
                currentArticle?.description = description
                currentArticle?.url = url
                currentArticle?.urlToImage = urlToImage
                currentArticle?.publishAt = publishedAt
                currentArticle?.content = content
                if (currentArticle != null)
                {
                    newsArticles?.add(currentArticle)
                }
            }// end of loop*/
        }
        catch(e:Exception){
            e.printStackTrace()
            d("Article Fetching...", e.message.toString())
        }
        /*
        Sorry na retrofit...kung dae taka pinili kang inot. Wag kna magtampo...
        At hindi na din ako galit, love, love, love na kita...hemmwaah <3 <3
        Tao kana nin output...
         */
        return newsArticles
    }

}