package com.example.masrafna.ui.navigation.fragments.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.masrafna.data.models.NewsModel
import com.example.masrafna.data.models.NotificationModel
import com.example.masrafna.databinding.NotificationItemBinding
import com.example.masrafna.ui.navigation.fragments.news.NewsAdapter

class NotificationAdapter(val context: Context, private val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    private var notificationList = ArrayList<NotificationModel>()

    inner class NotificationViewHolder(val binding: NotificationItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = NotificationItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val currentNotification = notificationList[position]

        with(holder) {

            binding.moreBtn.setOnClickListener {
                clickListener.onItemClicked(currentNotification)
            }

            binding.date.text = currentNotification.date
            binding.title.text = currentNotification.title
            binding.desc.text = currentNotification.desc
            binding.image.setImageResource(currentNotification.image)


        }


    }

    override fun getItemCount(): Int {
        return notificationList.size
    }

    fun submitArticles(notificationsList: ArrayList<NotificationModel>) {
        this.notificationList = notificationsList
        notifyDataSetChanged()
    }

    interface OnItemClickListener{
        fun onItemClicked(notification: NotificationModel)
    }

}