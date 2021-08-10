package com.example.masrafna.ui.navigation.fragments.home

import android.animation.ArgbEvaluator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.masrafna.data.models.CardHomeFragmentModel
import com.example.masrafna.databinding.FragmentHomeBinding
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.findNavController

import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.example.masrafna.R
import com.example.masrafna.data.models.ArticleHomeFragmentModel


class HomeFragment() : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private lateinit var viewPagerHomeFragmentAdapter: ViewPagerHomeFragmentAdapter
    private lateinit var articleHomeFragmentAdapter: ArticleHomeFragmentAdapter
    private var models: ArrayList<CardHomeFragmentModel>? = null
    private var articles = ArrayList<ArticleHomeFragmentModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getViewPagerItems()

        getArticles()

        binding.newsCard.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_home_to_fragment_news)
        }

    }

    /**
     * request articles from api for populate it
     */
    private fun getArticles() {


        // dummy list models
        articles = arrayListOf(
            ArticleHomeFragmentModel(
                R.drawable.brochure,
                "Brochure",
                " this is information for this card"
            ),
            ArticleHomeFragmentModel(
                R.drawable.sticker,
                "sticker",
                " this is information for this card"
            ),
            ArticleHomeFragmentModel(
                R.drawable.poster,
                "poster",
                " this is information for this card"
            ),
            ArticleHomeFragmentModel(
                R.drawable.namecard,
                "namecard",
                " this is information for this card"
            )
        )


        // when we get data from server we can now set the view pager
        populateArticles()

    }

    private fun populateArticles() {
        articleHomeFragmentAdapter = ArticleHomeFragmentAdapter(requireContext())
        articleHomeFragmentAdapter.submitArticles(articles)
        with(binding.articlesRv) {
            adapter = articleHomeFragmentAdapter

        }
    }

    /**
     * request data from api for populate it with view pager
     */
    private fun getViewPagerItems() {


        // dummy list models
        models = arrayListOf(
            CardHomeFragmentModel(
                R.drawable.brochure,
                "Brochure",
                " this is information for this card"
            ),
            CardHomeFragmentModel(
                R.drawable.sticker,
                "sticker",
                " this is information for this card"
            ),
            CardHomeFragmentModel(
                R.drawable.poster,
                "poster",
                " this is information for this card"
            ),
            CardHomeFragmentModel(
                R.drawable.namecard,
                "namecard",
                " this is information for this card"
            )
        )

        // when we get data from server we can now set the view pager
        populateViewPager()


    }

    private fun populateViewPager() {
        viewPagerHomeFragmentAdapter = ViewPagerHomeFragmentAdapter(requireContext())
        viewPagerHomeFragmentAdapter.submitList(models!!)

        with(binding.viewPager) {

            adapter = viewPagerHomeFragmentAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
            val offsetPx = resources.getDimensionPixelOffset(R.dimen.offset)
            setPageTransformer { page, position ->
                val viewPager = page.parent.parent as ViewPager2
                val offset = position * -(2 * offsetPx + pageMarginPx)
                if (viewPager.orientation == ORIENTATION_HORIZONTAL) {
                    if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                        page.translationX = -offset
                    } else {
                        page.translationX = offset
                    }
                } else {
                    page.translationY = offset
                }
            }


        }
    }
}