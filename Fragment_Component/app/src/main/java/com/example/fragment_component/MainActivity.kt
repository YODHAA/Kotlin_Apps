package com.example.fragment_component

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragment_component.api.NewsApiService
import com.example.fragment_component.data.Article
import com.example.fragment_component.data.NewsArticles
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),Callback<NewsArticles>{

    val data=ArrayList<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  supportFragmentManager.beginTransaction().add(R.id.container,BlankFragment()).commit()

        var newsData=NewsApiService.create()
        newsData.getTopHighlights().enqueue(this)

        recyclerview.layoutManager= LinearLayoutManager(this)
        recyclerview.adapter=NewsAdapter(data,this)

    }

    override fun onFailure(call: Call<NewsArticles>?, t: Throwable?) {

    }

    override fun onResponse(call: Call<NewsArticles>?, response: Response<NewsArticles>) {

        val newsArticles=response?.body()

        if(newsArticles!=null){
            data.clear()
            data.addAll(newsArticles.articles)
            recyclerview.adapter?.notifyDataSetChanged()
        }

        Log.i("Response A",newsArticles?.status)
        Log.i("Response B",newsArticles?.totalResults.toString())
        Log.i("Response C",newsArticles?.articles.toString())
        Log.i(" Response Message", response?.body().toString())
    }

//    override fun onFailure(call: Call<ResponseBody>, t: Throwable?) {
//            Log.e("Fail",t?.localizedMessage)
//    }
//
//    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//            Log.e(" Response Message", response?.body()?.string())
//    }
}
