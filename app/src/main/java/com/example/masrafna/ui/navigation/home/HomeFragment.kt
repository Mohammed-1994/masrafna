package com.example.masrafna.ui.navigation.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import com.example.masrafna.data.models.CardHomeFragmentModel
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer

import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.example.masrafna.R
import com.example.masrafna.data.models.ArticleHomeFragmentModel
import com.example.masrafna.databinding.ActivityNavigationDrawerBinding
import com.example.masrafna.databinding.FragmentHomeBinding
import com.example.masrafna.ui.navigation.NavigationDrawerActivity
import com.example.masrafna.ui.services.ServicesActivity
import kotlin.math.abs


private const val TAG = "HomeFragment myTag"

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
        setupToolbar()
        getArticles()
        getToken()



        binding.newsCard.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_home_to_fragment_news)
        }
        binding.featuresCard.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_nav_services_list)
        }
        binding.banksCard.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_nav_banksListFragment)
        }

    }

    private fun setupToolbar() {

        with(binding) {
            toolbar.drawerIcon.setOnClickListener {
                (requireContext() as NavigationDrawerActivity)
                    .binding.drawerLayout.open()
            }
        }

        if (!resources.getBoolean(R.bool.is_right_to_left)) {
            binding.newsGo.rotation = 180f
            binding.banksGo.rotation = 180f
            binding.mainFeaturesGo.rotation = 180f
        }
    }

    private fun getToken() {
        val sharedPref = activity?.getSharedPreferences(
            getString(R.string.access_token_preferences), Context.MODE_PRIVATE
        )
        val token = sharedPref?.getString(getString(R.string.access_token), null)

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
                R.drawable._30736ee2c2cfb6bce43bce2737d65c85,
                "Brochure",
                " this is information for this card"
            ),
            CardHomeFragmentModel(
                R.drawable._3764e9a41fff66432dfa4290f16ecd35,
                "sticker",
                " this is information for this card"
            ),
            CardHomeFragmentModel(
                R.drawable.card__,
                "poster",
                " this is information for this card"
            ),
            CardHomeFragmentModel(
                R.drawable.card_,
                "namecard",
                " this is information for this card"
            ),
            CardHomeFragmentModel(
                R.drawable.card___,
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


            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
            val offsetPx = resources.getDimensionPixelOffset(R.dimen.offset)
            val tt = ViewPager2.PageTransformer { page, position ->
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


            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            ViewPager2.PageTransformer { page, position ->
                page.apply {
                    translationY = abs(position) * 500f
                    scaleX = 0.5f
                    scaleY = 0.5f
                }
            }

//            val marginPageTransformer = MarginPageTransformer(50)
            setPageTransformer(CompositePageTransformer().also {
                it.addTransformer(tt)
//                it.addTransformer(marginPageTransformer)
                it.addTransformer(ViewPager2PageTransformation())
            })
            adapter = viewPagerHomeFragmentAdapter
        }
    }

    class ViewPager2PageTransformation : ViewPager2.PageTransformer {
        override fun transformPage(page: View, position: Float) {
            val absPos = Math.abs(position)
            page.apply {

                translationY = absPos * -70f

                val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
                val offsetPx = resources.getDimensionPixelOffset(R.dimen.offset)
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


//                when {
//                    position < -1 ->
//                        page.alpha = 0.1f
//                    position <= 1 -> {
//                        page.alpha = Math.max(0.2f, 1 - Math.abs(position))
//                    }
//                    else -> page.alpha = 1f
//                }


            }
        }
    }
}