package com.example.masrafna.ui.navigation.news

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.masrafna.R
import com.example.masrafna.data.models.NewsModel
import com.example.masrafna.databinding.FragmentNewsBinding

private const val TAG = "NewsFragment myTag"

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val news = arguments?.getParcelable<NewsModel>("news")
        showNewsDetails(news)
    }

    private fun showNewsDetails(news: NewsModel?) {
        if (news != null) {
            binding.date.text = news.date
            binding.title.text = news.title
            binding.desc.text = news.desc
            binding.image.setImageResource(news.image)
        }
    }


}