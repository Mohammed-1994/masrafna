package com.example.masrafna.ui.spalsh

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.masrafna.R
import com.example.masrafna.databinding.FragmentFirstBinding
import com.example.masrafna.databinding.FragmentSecondBinding
import com.example.masrafna.databinding.FragmentSignupBinding

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_fragment_second_to_fragment_third)
        }
    }
}