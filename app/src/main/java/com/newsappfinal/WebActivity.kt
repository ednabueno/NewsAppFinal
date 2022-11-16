package com.newsappfinal


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.newsappfinal.databinding.ActivityWebBinding
import com.newsappfinal.newComponent.NewsComponent

class WebActivity : AppCompatActivity(){
    private lateinit var binding: ActivityWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var webView : WebView = binding.webView

        var intent: Intent? = getIntent()
        var url = intent!!.getStringExtra("url_key")
        webView.loadUrl(url!!)
    }

    override fun onRestart() {
        super.onRestart()
        finish()
    }

}