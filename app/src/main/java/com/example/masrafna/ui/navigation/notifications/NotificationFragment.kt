package com.example.masrafna.ui.navigation.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.masrafna.R
import com.example.masrafna.data.models.NotificationModel
import com.example.masrafna.databinding.FragmentNotificationBinding
import com.example.masrafna.ui.navigation.NavigationDrawerActivity

class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val notification = arguments?.getParcelable<NotificationModel>("notification")

        setupToolbar()
        showNotificationDetails(notification)
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
            toolbar.title.visibility = View.INVISIBLE
            if (!resources.getBoolean(R.bool.is_right_to_left)) {
                toolbar.navigateUp.rotation = 180f
            }
        }
    }

    private fun showNotificationDetails(notification: NotificationModel?) {
        if (notification != null) {
            binding.date.text = notification.date
            binding.titleTv.text = notification.title
            binding.desc.text = notification.desc
            binding.notificationImage.setImageResource(notification.image)
        }
    }

}