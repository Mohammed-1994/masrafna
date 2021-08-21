package com.example.masrafna.ui.navigation.news

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.masrafna.R
import com.example.masrafna.data.models.NewsModel
import com.example.masrafna.data.models.NotificationModel
import com.example.masrafna.databinding.FragmentNewsBinding
import com.example.masrafna.databinding.FragmentNewsListBinding
import com.example.masrafna.ui.navigation.NavigationDrawerActivity
import com.example.masrafna.ui.navigation.notifications.NotificationAdapter

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
        getArticles()

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

    /**
     * request notifications from api for populate it
     */
    private fun getArticles() {

        // dummy list models
        newsList = arrayListOf(
            NewsModel(
                R.drawable.brochure,
                "Brochure",
                "من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاعمن الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع  ",
                "13/6/1994"
            ),
            NewsModel(
                R.drawable.sticker,
                "sticker",
                "من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاعمن الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع  ",
                "13/6/1994"
            ),
            NewsModel(
                R.drawable.poster,
                "poster",
                " من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاعمن الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع  ",
                "13/6/1994"
            ),
            NewsModel(
                R.drawable.namecard,
                "namecard",
                " من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاعمن الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع  ",
                "13/6/1994"
            )
        )


        // when we get data from server we can now set the recycler view
        populateArticles()

    }


    private fun populateArticles() {
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