package com.newsappfinal.presenter

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import com.newsappfinal.model.ArticleData
import com.newsappfinal.model.ArticleModel
import com.newsappfinal.newComponent.NewsComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class Presenter: NewsComponent.Presenter {
    private var articleModel: ArticleModel = ArticleModel()
    @OptIn(ExperimentalStdlibApi::class)
    override suspend fun getArticles(apiKey:String): ArrayList<ArticleData>?
    {
        return articleModel.getNewsFromApi(apiKey)

    }

//    override fun pBarVisibility(pbar: ProgressBar) {
//        if(articleModel != null){
//            pbar.visibility = View.GONE
//        }
//    }

}