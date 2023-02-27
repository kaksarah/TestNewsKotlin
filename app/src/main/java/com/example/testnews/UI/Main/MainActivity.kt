package com.example.testnews.UI.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testnews.Data.Remote.ApiClient
import com.example.testnews.Data.Response.ArticlesItem
import com.example.testnews.Data.Response.NewsResponse
import com.example.testnews.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews()
        initRecycleView()
    }

    private fun initRecycleView() {
        adapter = NewsAdapter {
            // OnClick
        }

        val rvNews = findViewById<RecyclerView>(R.id.rvNews)

        rvNews.layoutManager = LinearLayoutManager(this)
        rvNews.adapter = adapter
    }

    private fun getNews() {
        ApiClient.create().getNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.isSuccessful) {
                    val articles : List<ArticlesItem> = response.body()?.articles as List<ArticlesItem>
                    if (articles != null){
                        adapter.setNews(articles)
                    }
                }

            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                // Tangani jika terjadi kesalahan saat melakukan request ke API
            }
        })
    }
}
