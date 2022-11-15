package com.newsappfinal.model

import com.google.gson.annotations.SerializedName


data class ArticleData(
    @SerializedName("author")
    var author:String?,

    @SerializedName("title")
    var title: String?,

    @SerializedName("description")
    var description: String?,

    @SerializedName("url")
    var url:String?,

    @SerializedName("urlToImage")
    var urlToImage: String?,

    @SerializedName("publishedAt")
    var publishAt: String?,

    @SerializedName("content")
    var content: String?
)
