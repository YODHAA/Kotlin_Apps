package com.example.fragment_component

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fragment_component.data.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_holder_layout.view.*

class NewsAdapter(val newsData: List<Article>,val ctx:Context) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){

             val newsText = view.newsText
             val newsImage=view.newsImage

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.view_holder_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
         return  newsData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.newsText.text=newsData[position].title
        Picasso.with(ctx)
            .load(newsData[position].urlToImage)
            .resize(350, 350)
            .into(holder.newsImage)

    }

}