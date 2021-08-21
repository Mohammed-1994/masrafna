package com.example.masrafna.ui.navigation.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.masrafna.R
import com.example.masrafna.data.models.NotificationModel
import com.example.masrafna.databinding.FragmentNotificationsListBinding
import com.example.masrafna.ui.navigation.NavigationDrawerActivity

private const val TAG = "NotificationsFr myTag"

class NotificationsListFragment : Fragment(), NotificationAdapter.OnItemClickListener {
    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var binding: FragmentNotificationsListBinding
    private var notifications = ArrayList<NotificationModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsListBinding.inflate(inflater, container, false)
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

            toolbar.title.text = getString(R.string.notifications)

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
        notifications = arrayListOf(
            NotificationModel(
                R.drawable.brochure,
                "Brochure",
                "  من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاعمن الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع ",
                "13/6/1994"
            ),
            NotificationModel(
                R.drawable.sticker,
                "sticker",
                "  من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاعمن الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع ",
                "13/6/1994"
            ),
            NotificationModel(
                R.drawable.poster,
                "poster",
                "  من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاعمن الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع ",
                "13/6/1994"
            ),
            NotificationModel(
                R.drawable.namecard,
                "namecard",
                "  من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاعمن الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع من الشخصيات التي ساهمت في تطوير القطاع المصرفي العراقي والتي تقوم دائما بابتكار النماذج الجديدة لتطوير القطاع المصرفي اضافة الى المبادرات المستمرة التي تقدم الدعم المستمر لهذا القطاع ",
                "13/6/1994"
            )
        )


        // when we get data from server we can now set the recycler view
        populateArticles()

    }


    private fun populateArticles() {
        notificationAdapter = NotificationAdapter(requireContext(), this)
        notificationAdapter.submitArticles(notifications)
        with(binding.notificationRv) {
            adapter = notificationAdapter

        }
    }

    override fun onItemClicked(notification: NotificationModel) {
        val bundle = Bundle()
        bundle.putParcelable("notification", notification)
        findNavController().navigate(R.id.action_fragment_notifications_list_to_fragment_notification, bundle)

    }

}