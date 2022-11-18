package com.newsappfinal.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.widget.ANImageView
import com.newsappfinal.R
import com.newsappfinal.WebActivity
import com.newsappfinal.model.ArticleData
import com.newsappfinal.newComponent.NewsComponent

class ViewHolderAdapter(var mContext:Context, var mArticles: ArrayList<ArticleData>? ): RecyclerView.Adapter<ViewHolderAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { //inflated of layout
        //recycler of the view holder
        var view:View = LayoutInflater.from(mContext).inflate(R.layout.article_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { //recycleview holder  reusing of the views
        val currentArticle : ArticleData = mArticles!![position]
        holder.title.text = currentArticle.title
        holder.description.text = currentArticle.description
        val author= currentArticle.author
        val dte: String = currentArticle.publishAt.toString().substring(0, 10)
        if(author == null){
            holder.contributorDate.text = dte
        }else{
            holder.contributorDate.text = author.plus(" | ").plus(dte) //Smart Cast
        }
        //loading the image
        holder.image.setErrorImageResId(R.drawable.ic_launcher_background)
        holder.image.setDefaultImageResId(R.drawable.ic_launcher_foreground)
        holder.image.setImageUrl(currentArticle.urlToImage)
        holder.setItemClickListener(object : NewsComponent.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                var intent: Intent = Intent(mContext, WebActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("url_key", currentArticle.url)
                mContext!!.startActivity(intent)
            }

        })
    }

    override fun getItemCount(): Int {
        return mArticles!!.size
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var itemClickListener: NewsComponent.ItemClickListener
        var title:TextView = view.findViewById(R.id.title_id)
        var description:TextView = view.findViewById(R.id.description_id)
        var contributorDate:TextView = view.findViewById(R.id.contributorDate)
        var image : ANImageView = view.findViewById(R.id.image_id)
        init{
            view.setOnClickListener(this)
        }
        fun setItemClickListener(itemClickListener: NewsComponent.ItemClickListener){
            this.itemClickListener = itemClickListener
        }
        override fun onClick(v: View?) {
            itemClickListener.onClick(v!!, adapterPosition)
        }
    }
}