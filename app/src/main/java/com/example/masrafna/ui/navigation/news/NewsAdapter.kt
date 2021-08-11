package com.example.masrafna.ui.navigation.news

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masrafna.data.models.NewsModel
import com.example.masrafna.databinding.NewsItemBinding

class NewsAdapter(val context: Context, val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsList = ArrayList<NewsModel>()

    inner class NewsViewHolder(val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = NewsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentNews = newsList[position]

        with(holder) {

            binding.moreBtn.setOnClickListener {
                clickListener.onItemClicked(
                    currentNews
                )
            }

            binding.date.text = currentNews.date
            binding.title.text = currentNews.title
            binding.desc.text = currentNews.desc
            binding.image.setImageResource(currentNews.image)


        }


    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun submitArticles(newsList: ArrayList<NewsModel>) {
        this.newsList = newsList
        notifyDataSetChanged()
    }

    interface OnItemClickListener{
        fun onItemClicked(news: NewsModel)
    }

}