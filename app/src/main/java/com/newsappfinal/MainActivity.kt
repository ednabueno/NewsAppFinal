package com.newsappfinal

import android.os.Bundle
import android.util.Log.i
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.newsappfinal.databinding.ActivityMainBinding
import com.newsappfinal.model.ArticleData
import com.newsappfinal.newComponent.NewsComponent
import com.newsappfinal.presenter.Presenter
import com.newsappfinal.view.ViewHolderAdapter
import io.reactivex.internal.operators.single.SingleDoOnSuccess
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), NewsComponent.View.MainVew{
    private lateinit var binding: ActivityMainBinding
    private var presenter: Presenter? = null
    private var pbar:ProgressBar? = null
    private var recyclerView:RecyclerView? = null
    private val apiKey:String = "98262df4f3a14d19a3b6cc84be8c004e"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var items: ArrayList<ArticleData>? = null
        pbar = binding.progressBar
        recyclerView = binding.recycleView

        presenter = Presenter()
        recyclerView?.layoutManager = LinearLayoutManager(this)

        pbar!!.visibility = View.VISIBLE
    }

    override suspend fun loadArticles(mArticles: ArrayList<ArticleData>?) {
        GlobalScope.async {
            val articleData: ArrayList<ArticleData>? = presenter?.getArticles(apiKey)
//            delay(10000)
            loadArticles(articleData)
        }.await()
        i("loadArticles", mArticles?.size.toString())
        if (mArticles != null)
        {
            pbar!!.visibility = View.GONE
            recyclerView?.adapter = ViewHolderAdapter(this, mArticles)
        }
    }
}