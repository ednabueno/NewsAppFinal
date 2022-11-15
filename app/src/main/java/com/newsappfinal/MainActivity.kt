package com.newsappfinal

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
    //private val apiKey:String = "98262df4f3a14d19a3b6cc84be8c004e"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

  //      var items: ArrayList<ArticleData>? = null
        pbar = binding.progressBar
        recyclerView = binding.recycleView

        presenter = Presenter(this)
        presenter.requestDataFromServer()
       // pbar!!.visibility = View.VISIBLE

        /*GlobalScope.launch(Dispatchers.Main){
            pbar!!.visibility = View.GONE
            val articleData: ArrayList<ArticleData> = presenter.getArticles(apiKey)
            i("LoadData", articleData.size.toString())
            recyclerView?.adapter = ViewHolderAdapter(this@MainActivity, articleData)
      }
        Thread.sleep(10000)

         */
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
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

//    override suspend fun loadArticles():ArrayList<ArticleData> {
//        var article: ArrayList<ArticleData>
//
//        var coroutine = CoroutineScope(Dispatchers.IO).async {
//            val articleData: ArrayList<ArticleData> = presenter.getArticles(apiKey)
//            i("coroutine", articleData.size.toString())
//            article = articleData
//            article
//        }
//
//        return coroutineScope {
//            withContext(Dispatchers.Default){
//                val a = coroutine.await()
//                i("coroutine", a.size.toString())
//                a
//            }
//
//        }
//
//        i("loadArticles", coroutine?.size.toString())
//
//
//    }
}