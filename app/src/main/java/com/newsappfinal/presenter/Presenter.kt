package com.newsappfinal.presenter

import android.util.Log.e
import android.util.Log.i
import com.newsappfinal.model.ArticleData
import com.newsappfinal.model.ArticleModel
import com.newsappfinal.newComponent.NewsComponent

class Presenter(var mView : NewsComponent.View): NewsComponent.Presenter, NewsComponent.Model.OnFinishedListener {
    private var articleModel = ArticleModel()
    //var artle:ArrayList<ArticleData> = arrayListOf()
    override fun requestData() {
       // if(artle.isEmpty()) {
            mView.showProgress()
            articleModel.getNewsArticles(this)
       // }else{
        //    e(":::", "may laog pa ang adapter")
       // }
    }

    override fun onFinished(articleList: ArrayList<ArticleData>) {
        i("onFinished", articleList.size.toString())
        //artle = articleList
        mView.loadArticles(articleList)
        mView.hideProgress()
    }

    override fun onFailure(t: Throwable) {
        mView.onResponseFailure(t)
        mView.hideProgress()
    }

}