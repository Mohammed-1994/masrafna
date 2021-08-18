package com.example.masrafna.ui.spalsh

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.masrafna.databinding.FragmentFourthBinding
import com.example.masrafna.ui.loging.LoggingActivity

class FourthFragment : Fragment() {
    private lateinit var binding: FragmentFourthBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginBtn.setOnClickListener {
            startActivity(Intent(requireContext(), LoggingActivity::class.java))
            requireActivity().finish()
        }
    }
}