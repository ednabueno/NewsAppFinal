package com.newsappfinal.newComponent

import com.newsappfinal.model.ArticleData

interface NewsComponent {
    interface View{
        fun showProgress()
        fun hideProgress()
        fun setDataToRecyclerView(mArcticles: ArrayList<ArticleData>)
        fun onResponseFailure(throwable: Throwable)
    }
    interface Model{
        interface OnFinishedListener{
            fun onFinished(mArcticles:ArrayList<ArticleData>)
            fun onFailure(t: Throwable)
        }
        fun getNewsArticles(onFinishedListener: OnFinishedListener)

    }

    interface Presenter{
        fun requestDataFromApi()

    }
    interface ItemClickListener{
            fun onClick(view: android.view.View, position:Int)
    }
}