package com.newsappfinal.presenter

import android.content.Context
import android.content.ContextParams
import android.util.Log.i
import com.newsappfinal.MainActivity
import com.newsappfinal.model.ArticleData
import com.newsappfinal.model.ArticleModel
import com.newsappfinal.newComponent.NewsComponent
import kotlinx.coroutines.*
import java.math.MathContext

class Presenter(var mView : NewsComponent.View): NewsComponent.Presenter, NewsComponent.Model.OnFinishedListener {
    /*private var articleModel: ArticleModel = ArticleModel()
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

     */

    //    override fun pBarVisibility(pbar: ProgressBar) {
//        if(articleModel != null){
//            pbar.visibility = View.GONE
//        }
//    }
    private var articleModel : NewsComponent.Model = ArticleModel()
    override fun onDestroy() {
       //finish()
    }

    override fun requestDataFromServer() {
        mView?.showProgress()
        articleModel.getNewsArticles(this)
    }

    override fun onFinished(mArcticles: ArrayList<ArticleData>) {
        i("onFinished", mArcticles.size.toString())
        mView.setDataToRecyclerView(mArcticles)
        mView?.hideProgress()
    }

    override fun onFailure(t: Throwable) {
        mView?.onResponseFailure(t)
        mView?.hideProgress()
    }

}