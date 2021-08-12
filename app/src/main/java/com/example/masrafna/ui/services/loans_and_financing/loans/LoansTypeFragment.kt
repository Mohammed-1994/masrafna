package com.example.masrafna.ui.services.loans_and_financing.loans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.masrafna.R
import com.example.masrafna.databinding.FragmentLoansTypeBinding
import com.example.masrafna.ui.services.loans_and_financing.LoansFinancingFragment.Companion.BANKS_LOANS
import com.example.masrafna.ui.services.loans_and_financing.LoansFinancingFragment.Companion.ISLAMIC_FINANCING

class LoansTypeFragment : Fragment() {

    private lateinit var binding: FragmentLoansTypeBinding
    private var viewType = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoansTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewType = requireArguments().getInt("view_type")

        updateView()

        binding.personal.setOnClickListener {
            val bundle = bundleOf("view_type" to viewType, "personal_company" to PERSONAL)
            findNavController().navigate(
                R.id.action_from_loans_type_to_personal_loans_fragment,
                bundle
            )
        }
        binding.company.setOnClickListener {
            val bundle = bundleOf("view_type" to viewType, "personal_company" to COMPANY)
            findNavController().navigate(
                R.id.action_from_loans_type_to_personal_loans_fragment,
                bundle
            )
        }
    }

    private fun updateView() {
        when (viewType) {
            BANKS_LOANS -> {
                with(binding) {
                    title.text = getString(R.string.bank_loans)
                    personal.text = getString(R.string.personal_loans)
                    company.text = getString(R.string.company_loans)
                }
            }
            ISLAMIC_FINANCING -> {
                with(binding) {
                    title.text = getString(R.string.islamic_financing)
                    personal.text = getString(R.string.personal_financing)
                    company.text = getString(R.string.company_financing)
                }
            }
        }
    }

    companion object {
        val PERSONAL = 1
        val COMPANY = 2
    }

}