package com.newsappfinal.newComponent

import android.content.Context
import android.widget.ProgressBar
import com.newsappfinal.model.ArticleData
import kotlinx.coroutines.CoroutineDispatcher

interface NewsComponent {
    interface View{
        //for testing
        fun showProgress()
        fun hideProgress()
        fun setDataToRecyclerView(mArcticles: ArrayList<ArticleData>)
        fun onResponseFailure(throwable: Throwable)
        //end of testing function

        interface MainVew{
            //suspend fun loadArticles():ArrayList<ArticleData>
        }
        interface WebView{
            fun getUrl():String
        }
    }
    interface Model{
        //for Testing
        interface OnFinishedListener{
            fun onFinished(mArcticles:ArrayList<ArticleData>)
            fun onFailure(t: Throwable)
        }
        fun getNewsArticles(onFinishedListener: OnFinishedListener)
        //end of Testing function

       // suspend fun getNewsFromApi(apiKey: String): ArrayList<ArticleData>
    }

    interface Presenter{
        //for testing
        fun onDestroy()
        fun requestDataFromServer()
        //end of testing Function

       // suspend fun getArticles(apiKey:String): ArrayList<ArticleData>?
//        fun pBarVisibility(pbar:ProgressBar)

    }
    interface ItemClickListener{
            fun onClick(view: android.view.View, position:Int)
    }
}