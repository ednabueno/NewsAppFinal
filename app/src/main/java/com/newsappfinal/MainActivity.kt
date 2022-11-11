package com.newsappfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.newsappfinal.databinding.ActivityMainBinding
import com.newsappfinal.model.ArticleData
import com.newsappfinal.presenter.Presenter
import com.newsappfinal.view.ViewHolderAdapter

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private var presenter: Presenter? = null
    private var pbar:ProgressBar? = null
    private var recyclerView:RecyclerView? = null
    private val apiKey:String = "98262df4f3a14d19a3b6cc84be8c004e"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //var items = arrayListOf<ArticleData>()
        pbar = binding.progressBar
        recyclerView = binding.recycleView

        recyclerView?.layoutManager = LinearLayoutManager(this)

        presenter = Presenter()
        presenter?.pBarVisibility(pbar!!)
        var articleData:ArrayList<ArticleData>? = presenter?.getArticles(apiKey, this)
        recyclerView?.adapter = ViewHolderAdapter(this, articleData)
    }
}