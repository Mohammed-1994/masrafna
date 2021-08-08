package com.example.masrafna.ui.navigation.fragments.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masrafna.data.models.ArticleHomeFragmentModel
import com.example.masrafna.databinding.MainFragmentArticleItemBinding

class ArticleHomeFragmentAdapter(context: Context) :
    RecyclerView.Adapter<ArticleHomeFragmentAdapter.ArticleViewHolder>() {

    private var articlesList = ArrayList<ArticleHomeFragmentModel>()


    inner class ArticleViewHolder(val binding: MainFragmentArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = MainFragmentArticleItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentArticle = articlesList[position]
        with(holder) {
            binding.image.setImageResource(currentArticle.image)
            binding.desc.text = currentArticle.desc
            binding.title.text = currentArticle.title
        }
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    fun submitArticles(articlesList: ArrayList<ArticleHomeFragmentModel>) {
        this.articlesList = articlesList
        notifyDataSetChanged()
    }


}