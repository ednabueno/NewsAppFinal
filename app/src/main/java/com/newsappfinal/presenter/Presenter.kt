package com.newsappfinal.presenter

import android.util.Log.i
import com.newsappfinal.model.ArticleData
import com.newsappfinal.model.ArticleModel
import com.newsappfinal.newComponent.NewsComponent

class Presenter(var mView : NewsComponent.View): NewsComponent.Presenter, NewsComponent.Model.OnFinishedListener {
    private var articleModel = ArticleModel()

    override fun requestData() {
        mView.showProgress()
        articleModel.getNewsArticles(this)
    }

    override fun onFinished(articleList: ArrayList<ArticleData>) {
        i("onFinished", articleList.size.toString())
        mView.loadArticles(articleList)
        mView.hideProgress()
    }

    override fun onFailure(t: Throwable) {
        mView.onResponseFailure(t)
        mView.hideProgress()
    }

}