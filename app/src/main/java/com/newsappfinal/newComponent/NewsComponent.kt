package com.newsappfinal.newComponent

import android.content.Context
import android.widget.ProgressBar
import com.newsappfinal.model.ArticleData

interface NewsComponent {
    interface View{
//        interface MainVew{
//            fun requestLoadData(mArticles: ArrayList<ArticleData>?)
//        }
        interface WebView{
            fun getUrl():String
        }
    }
    interface Model{
        fun getNewsFromApi(apiKey: String, mContext: Context): ArrayList<ArticleData>?
    }

    interface Presenter{
        fun getArticles(apiKey:String, mContext: Context): ArrayList<ArticleData>?
        fun pBarVisibility(pbar:ProgressBar)
    }
    interface ItemClickListener{
            fun onClick(view: android.view.View, position:Int)
    }
}