package com.newsappfinal

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log.e
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

class MainActivity : AppCompatActivity(), NewsComponent.View{
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: Presenter
    private var pbar:ProgressBar? = null
    private var recyclerView:RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pbar = binding.progressBar
        recyclerView = binding.recycleView

        presenter = Presenter(this)
        presenter.requestDataFromServer()

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

    override fun setDataToRecyclerView(mArcticles: ArrayList<ArticleData>) {
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = ViewHolderAdapter(this@MainActivity, mArcticles)
    }

    override fun onResponseFailure(throwable: Throwable) {
        e("MainActivity", throwable.message.toString())
        Toast.makeText(this@MainActivity, throwable.message.toString(), Toast.LENGTH_LONG).show()
        finish()
    }
}