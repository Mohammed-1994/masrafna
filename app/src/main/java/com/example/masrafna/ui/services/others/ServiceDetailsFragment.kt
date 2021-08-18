package com.example.masrafna.ui.services.others

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import com.example.masrafna.R
import com.example.masrafna.databinding.FragmentServiceDetailsBinding

class ServiceDetailsFragment : Fragment() {

    private lateinit var binding: FragmentServiceDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentServiceDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scroll.fullScroll(ScrollView.FOCUS_UP)
        updateView()

    }

    private fun updateView() {
        val title = requireArguments().getString("id").toString()
        binding.title.text = title
    }
}