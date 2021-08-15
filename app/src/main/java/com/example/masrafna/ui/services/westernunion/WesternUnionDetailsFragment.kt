package com.example.masrafna.ui.services.westernunion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.masrafna.R
import com.example.masrafna.databinding.FragmentWesternUnionDetailsBinding

class WesternUnionDetailsFragment : Fragment() {

    private lateinit var binding: FragmentWesternUnionDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWesternUnionDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = requireArguments().getString("id")
        with(binding) {
            title1.text = "التحويل بواسطة بنك $title"
        }
    }

}