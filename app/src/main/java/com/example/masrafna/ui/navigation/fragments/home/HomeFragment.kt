package com.example.masrafna.ui.navigation.fragments.home

import android.animation.ArgbEvaluator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.masrafna.R
import androidx.viewpager.widget.ViewPager
import com.example.masrafna.data.models.HomeFragmentCard
import com.example.masrafna.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    lateinit var adapter: HomeFragmentAdapter
    var models: MutableList<HomeFragmentCard>? = null
    var colors: Array<Int>? = null
    var argbEvaluator: ArgbEvaluator = ArgbEvaluator()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        models = mutableListOf(
            HomeFragmentCard(R.drawable.brochure, "Brochure", " this is information for this card"),
            HomeFragmentCard(R.drawable.sticker, "sticker", " this is information for this card"),
            HomeFragmentCard(R.drawable.poster, "poster", " this is information for this card"),
            HomeFragmentCard(R.drawable.namecard, "namecard", " this is information for this card")
        )

        val coloresTemp = arrayOf(
            resources.getColor(R.color.purple_200),
            resources.getColor(R.color.black),
            resources.getColor(R.color.white),
            resources.getColor(R.color.purple_500),
            resources.getColor(R.color.purple_700),
            resources.getColor(R.color.teal_700),
            resources.getColor(R.color.teal_200),

            )

        colors = coloresTemp

        adapter = HomeFragmentAdapter(models!!, requireContext())

        binding.viewPager.adapter = adapter

        binding.viewPager.setPadding(50, 0, 50, 0)


    }


}