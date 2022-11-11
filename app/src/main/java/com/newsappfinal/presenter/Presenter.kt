package com.newsappfinal.presenter

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import com.newsappfinal.model.ArticleData
import com.newsappfinal.model.ArticleModel
import com.newsappfinal.newComponent.NewsComponent

class Presenter: NewsComponent.Presenter {
    private var articleModel: ArticleModel = ArticleModel()
    override fun getArticles(apiKey:String, mContext: Context): ArrayList<ArticleData>? {
        return articleModel.getNewsFromApi(apiKey, mContext)
    }

    override fun pBarVisibility(pbar: ProgressBar) {
        if(articleModel != null){
            pbar.visibility = View.GONE
        }
    }

}