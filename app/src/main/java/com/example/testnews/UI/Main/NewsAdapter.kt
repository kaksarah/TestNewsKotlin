package com.example.testnews.UI.Main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.testnews.Data.Response.ArticlesItem
import com.example.testnews.R
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class NewsAdapter (private val listener : (ArticlesItem)-> Unit) : RecyclerView.Adapter<NewsAdapter.ViewHolder>()  {

    private var news : List<ArticlesItem> = listOf<ArticlesItem>()

    fun setNews(news:List<ArticlesItem>) {
        this.news = news
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int {
        return  news.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val ivNews: ImageView = itemView.findViewById(R.id.ivNews)

        fun bind(news: ArticlesItem) {
            tvTitle.text = news.title

            Glide.with(itemView.context)
                .load(news.urlToImage)
                .apply(RequestOptions()
                    .dontTransform()
                    .placeholder(R.drawable.image))
                .into(ivNews);


            itemView.setOnClickListener {
                listener(news)

            }
        }
    }
}