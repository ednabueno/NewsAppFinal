package com.newsappfinal.presenter

import com.newsappfinal.model.ArticleData
import com.newsappfinal.model.ArticleModel
import com.newsappfinal.newComponent.NewsComponent
import kotlinx.coroutines.*

class Presenter: NewsComponent.Presenter {
    private var articleModel: ArticleModel = ArticleModel()
    override suspend fun getArticles(apiKey:String): ArrayList<ArticleData>
    {
        val coroutine = CoroutineScope(Dispatchers.IO).async{
            val article:ArrayList<ArticleData> = articleModel.getNewsFromApi(apiKey)
            article
        }
        return coroutineScope {
            withContext(Dispatchers.Default){
                val a = coroutine.await()
                a
            }
        }
    }

//    override fun pBarVisibility(pbar: ProgressBar) {
//        if(articleModel != null){
//            pbar.visibility = View.GONE
//        }
//    }

}