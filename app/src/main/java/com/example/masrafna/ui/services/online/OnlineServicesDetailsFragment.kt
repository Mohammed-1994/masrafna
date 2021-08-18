package com.example.masrafna.ui.services.online

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import com.example.masrafna.R
import com.example.masrafna.databinding.FragmentOnlineServicesDetailsBinding

class OnlineServicesDetailsFragment : Fragment() {

    private lateinit var binding: FragmentOnlineServicesDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnlineServicesDetailsBinding.inflate(inflater, container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = requireArguments().getString("id")
        binding.scroll.fullScroll(ScrollView.FOCUS_UP)
        with(binding) {
            title1.text = "خدمات الانترنت المصرفية عبر بنك$title"
        }
    }
}