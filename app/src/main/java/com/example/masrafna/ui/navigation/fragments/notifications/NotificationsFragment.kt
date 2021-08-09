package com.example.masrafna.ui.navigation.fragments.notifications

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.masrafna.R
import com.example.masrafna.data.models.ArticleHomeFragmentModel
import com.example.masrafna.data.models.NotificationModel
import com.example.masrafna.databinding.FragmentNotificationsBinding
import com.example.masrafna.ui.navigation.fragments.home.ArticleHomeFragmentAdapter

private const val TAG = "NotificationsFr myTag"

class NotificationsFragment : Fragment() {
    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var binding: FragmentNotificationsBinding
    private var notifications = ArrayList<NotificationModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArticles()
    }


    /**
     * request notifications from api for populate it
     */
    private fun getArticles() {

        // dummy list models
        notifications = arrayListOf(
            NotificationModel(
                R.drawable.brochure,
                "Brochure",
                " this is information for this card",
                "13/6/1994"
            ),
            NotificationModel(
                R.drawable.sticker,
                "sticker",
                " this is information for this card",
                "13/6/1994"
            ),
            NotificationModel(
                R.drawable.poster,
                "poster",
                " this is information for this card",
                "13/6/1994"
            ),
            NotificationModel(
                R.drawable.namecard,
                "namecard",
                " this is information for this card",
                "13/6/1994"
            )
        )


        // when we get data from server we can now set the recycler view
        populateArticles()

    }


    private fun populateArticles() {
        notificationAdapter = NotificationAdapter(requireContext())
        notificationAdapter.submitArticles(notifications)
        with(binding.notificationRv) {
            adapter = notificationAdapter

        }
    }

}