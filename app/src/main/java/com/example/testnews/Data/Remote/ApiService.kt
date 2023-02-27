package com.example.testnews.Data.Remote

import com.example.testnews.Data.Response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("top-headlines?country=id&apiKey=4093bd000ccd4e32a16d923149c2eb4a")

    fun getNews() : Call<NewsResponse>
}