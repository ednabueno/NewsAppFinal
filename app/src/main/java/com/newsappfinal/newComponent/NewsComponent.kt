package com.newsappfinal.newComponent

import android.content.Context
import android.widget.ProgressBar
import com.newsappfinal.model.ArticleData
import kotlinx.coroutines.CoroutineDispatcher

interface NewsComponent {
    interface View{
        interface MainVew{
            //suspend fun loadArticles():ArrayList<ArticleData>
        }
        interface WebView{
            fun getUrl():String
        }
    }
    interface Model{
        suspend fun getNewsFromApi(apiKey: String): ArrayList<ArticleData>
    }

    interface Presenter{
        suspend fun getArticles(apiKey:String): ArrayList<ArticleData>?
//        fun pBarVisibility(pbar:ProgressBar)

    }
    interface ItemClickListener{
            fun onClick(view: android.view.View, position:Int)
    }
}