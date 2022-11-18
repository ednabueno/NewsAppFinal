package com.newsappfinal.presenter

import android.util.Log.i
import com.newsappfinal.model.ArticleData
import com.newsappfinal.model.ArticleModel
import com.newsappfinal.newComponent.NewsComponent

class Presenter(var mView : NewsComponent.View): NewsComponent.Presenter, NewsComponent.Model.OnFinishedListener {
    private var articleModel = ArticleModel()

    override fun requestDataFromApi() {
        mView.showProgress()
        articleModel.getNewsArticles(this)
    }

    override fun onFinished(mArcticles: ArrayList<ArticleData>) {
        i("onFinished", mArcticles.size.toString())
        mView.setDataToRecyclerView(mArcticles)
        mView.hideProgress()
    }

    override fun onFailure(t: Throwable) {
        mView.onResponseFailure(t)
        mView.hideProgress()
    }

}