package com.newsappfinal.model

import com.google.gson.annotations.SerializedName

data class Website (
    @SerializedName("articles")
    val arrayListOfArticles: ArrayList<ArticleData>
)