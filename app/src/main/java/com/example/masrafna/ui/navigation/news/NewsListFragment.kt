package com.example.masrafna.ui.navigation.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.masrafna.R
import com.example.masrafna.data.models.NewsModel
import com.example.masrafna.databinding.FragmentNewsListBinding
import com.example.masrafna.ui.navigation.NavigationDrawerActivity

private const val TAG = "NewsListFragment myTag"

class NewsListFragment : Fragment(), NewsAdapter.OnItemClickListener {

    private lateinit var binding: FragmentNewsListBinding
    private var newsList = ArrayList<NewsModel>()
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()


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

            toolbar.title.text = getString(R.string.news)

            if (!resources.getBoolean(R.bool.is_right_to_left)) {
                toolbar.navigateUp.rotation = 180f
            }
        }
    }


    private fun populateNews() {
        newsAdapter = NewsAdapter(requireContext(), this)
        newsAdapter.submitArticles(newsList)
        with(binding.newsRv) {
            adapter = newsAdapter

        }
    }

    override fun onItemClicked(news: NewsModel) {
        val bundle = Bundle()
        bundle.putParcelable("news", news)
        findNavController().navigate(R.id.action_fragment_news_list_to_fragment_news, bundle)
    }


}