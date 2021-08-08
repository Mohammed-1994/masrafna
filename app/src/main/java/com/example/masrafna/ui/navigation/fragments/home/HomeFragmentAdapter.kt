package com.example.masrafna.ui.navigation.fragments.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.example.masrafna.R
import com.example.masrafna.data.models.HomeFragmentCard
import com.example.masrafna.databinding.MainViewPagerItemBinding

class HomeFragmentAdapter(val list: List<HomeFragmentCard>, private val context: Context) :
    PagerAdapter() {

    private lateinit var binding: MainViewPagerItemBinding
    private lateinit var layoutInflater: LayoutInflater


    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        binding = MainViewPagerItemBinding.inflate(layoutInflater, container, false)
        val view = layoutInflater.inflate(R.layout.main_view_pager_item, container, false)

        binding.image.setImageResource(list[position].image)

        container.addView(binding.root, 0)

        binding.root.setOnClickListener {
            Toast.makeText(context, list[position].title, Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


}