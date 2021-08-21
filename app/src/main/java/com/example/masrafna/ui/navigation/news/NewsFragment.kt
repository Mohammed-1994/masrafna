package com.example.masrafna.ui.navigation.news

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.masrafna.R
import com.example.masrafna.data.models.NewsModel
import com.example.masrafna.databinding.FragmentNewsBinding
import com.example.masrafna.ui.navigation.NavigationDrawerActivity

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
        setupToolbar()
        showNewsDetails(news)
    }

    private fun setupToolbar() {

        with(binding) {
            toolbar.drawerIcon.setOnClickListener {
                (requireContext() as NavigationDrawerActivity)
                    .binding.drawerLayout.open()
            }
            toolbar.navigateUp.setOnClickListener {
                findNavController().navigateUp()
            }
            toolbar.title.visibility = INVISIBLE
            if (!resources.getBoolean(R.bool.is_right_to_left)) {
                toolbar.navigateUp.rotation = 180f
            }
        }
    }

    private fun showNewsDetails(news: NewsModel?) {
        if (news != null) {
            binding.date.text = news.date
            binding.titleTv.text = news.title
            binding.desc.text = news.desc
            binding.newsImage.setImageResource(news.image)
        }
    }


}