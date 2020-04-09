package com.example.fragment_component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fragment_component.data.Article
import kotlinx.android.synthetic.main.view_holder_layout.view.*

class NewsAdapter(val newsData: List<Article>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
             val newsImage=view.newsImage
             val newsText = view.newsText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.view_holder_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
         return  newsData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.newsText.text=newsData[position].description
    }

}