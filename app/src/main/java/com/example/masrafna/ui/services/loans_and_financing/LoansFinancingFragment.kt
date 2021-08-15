package com.example.masrafna.ui.services.loans_and_financing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.masrafna.R
import com.example.masrafna.databinding.FragmentLoansFinancingBinding

class LoansFinancingFragment : Fragment() {
    private lateinit var binding: FragmentLoansFinancingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoansFinancingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.islamicFinancing.setOnClickListener {
            val bundle = bundleOf("view_type" to ISLAMIC_FINANCING)
            findNavController().navigate(R.id.action_from_loans_financing_to_loans_type_fragment, bundle)

        }

        binding.bankLoans.setOnClickListener {
            val bundle = bundleOf("view_type" to BANKS_LOANS)
            findNavController().navigate(R.id.action_from_loans_financing_to_loans_type_fragment, bundle)
        }
    }

    companion object {
        const val BANKS_LOANS = 1
        const val ISLAMIC_FINANCING = 2
    }

}