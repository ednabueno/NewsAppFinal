package com.newsappfinal

import android.os.Bundle
import android.util.Log.e
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.newsappfinal.databinding.ActivityMainBinding
import com.newsappfinal.model.ArticleData
import com.newsappfinal.newComponent.NewsComponent
import com.newsappfinal.presenter.Presenter
import com.newsappfinal.view.ViewHolderAdapter

class MainActivity : AppCompatActivity(), NewsComponent.View{
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: Presenter
    private var pbar:ProgressBar? = null
    private var recyclerView:RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        pbar = binding.progressBar
        recyclerView = binding.recycleView

        presenter = Presenter(this)
        //I think I need a dependency injection for this one.
        presenter.requestData() //unbinding and binding on the activity

    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }

    override fun showProgress() {
        pbar?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbar?.visibility = View.GONE
    }

    override fun loadArticles(articleList: ArrayList<ArticleData>) {
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = ViewHolderAdapter(this@MainActivity, articleList)
    }

    override fun onResponseFailure(throwable: Throwable) {
        e("MainActivity", throwable.message.toString())
        Toast.makeText(this@MainActivity, throwable.message.toString(), Toast.LENGTH_LONG).show()
        finish()
    }
}