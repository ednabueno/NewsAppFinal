package com.newsappfinal

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.util.Log.e
import android.util.Log.i
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
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
    private lateinit var pbar:ProgressBar
    private lateinit var recyclerView:RecyclerView
    //private var adapter: ViewHolderAdapter? = null
    private val scroll_state = "recycleState"
    private var recyclerViewState : Parcelable? = null
    //parcelable use to communicate between different components of the application, system components
    //and other applications and pass the data.
    //Interface for classes whose instances can be written to and restored from Parcel
    //Parcel - Container for a Message (data or objects references) that can be through IBinder


    override fun onCreate(savedInstanceState: Bundle?) {  //Bundle is a special type-safe container
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        pbar = binding.progressBar
        recyclerView = binding.recycleView

        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

//        if(savedInstanceState != null){
//            recyclerViewState = savedInstanceState.getParcelable(scroll_state)
//        }

        presenter = Presenter(this)
        presenter.requestData()

    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        recyclerViewState =recyclerView.layoutManager?.onSaveInstanceState() //save the scroll position of the layout manager
//        outState.putParcelable(scroll_state, recyclerViewState) //putParcelable(key:String, value:Parcelable)
//        super.onSaveInstanceState(outState)
//        e("::::", "onSaveInstance: " + recyclerViewState.toString())
//    }

//    override fun onRestoreInstanceState(state: Bundle) {
//        super.onRestoreInstanceState(state)
//        if(recyclerViewState != null){
//            recyclerViewState = state.getParcelable(scroll_state)
//        }
//
//        e(":::", "onRestore" )
//    }


    override fun showProgress() {
        pbar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbar.visibility = View.GONE
    }

    override fun loadArticles(articleList: ArrayList<ArticleData>) {
        var adapter = ViewHolderAdapter(this@MainActivity, articleList)
        recyclerView.adapter = adapter
        i("loadArticles", "Fetched")
    }


    override fun onResponseFailure(throwable: Throwable) {
        e("MainActivity", throwable.message.toString())
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Alert")
            builder.setMessage("Connection Interrupted: ".plus(throwable.message.toString()))
            builder.setPositiveButton("Try Again") { _, _ ->
                // (Dialog Interface, Int) -> Unit this function represented as High-Order Function
                // Dialog Interface instance of the dialog, int the ID of the button clicked
                //_ is used as passed if the arguments aren't used.
                presenter.requestData()
            }
            builder.setNegativeButton("Cancel") { _, _ ->
                finish()
            }
            builder.show()
    }
}