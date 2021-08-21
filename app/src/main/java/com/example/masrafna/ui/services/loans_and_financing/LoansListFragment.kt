package com.example.masrafna.ui.services.loans_and_financing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.masrafna.R
import com.example.masrafna.databinding.FragmentLoansListBinding
import com.example.masrafna.ui.navigation.NavigationDrawerActivity
import com.example.masrafna.ui.services.loans_and_financing.LoansFinancingFragment.Companion.BANKS_LOANS
import com.example.masrafna.ui.services.loans_and_financing.LoansFinancingFragment.Companion.ISLAMIC_FINANCING
import com.example.masrafna.ui.services.loans_and_financing.LoansTypeFragment.Companion.COMPANY
import com.example.masrafna.ui.services.loans_and_financing.LoansTypeFragment.Companion.PERSONAL

class LoansListFragment : Fragment() {

    private lateinit var binding: FragmentLoansListBinding
    private var viewType = 1
    private var personalOrCompany = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoansListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupToolbar()
        viewType = requireArguments().getInt("view_type")
        personalOrCompany = requireArguments().getInt("personal_company")


        updateView()
        val clicked = object : View.OnClickListener {
            override fun onClick(v: View?) {
                val title = (v as Button).text
                val bundle = bundleOf("loan_id" to title)

                findNavController().navigate(
                    R.id.action_from_personal_loans_list_to_personal_loan_fragment, bundle
                )
            }
        }

        binding.personalLoans1.setOnClickListener(clicked)
        binding.personalLoans2.setOnClickListener(clicked)
        binding.personalLoans3.setOnClickListener(clicked)

    }

    private fun setupToolbar() {

        with(binding) {
            toolbar.drawerIcon.setOnClickListener {
                (requireContext() as NavigationDrawerActivity)
                    .binding.drawerLayout.open()
            }
            toolbar.navigateUp.setOnClickListener {
                findNavController().navigateUp()
            }

            if (!resources.getBoolean(R.bool.is_right_to_left)) {
                toolbar.navigateUp.rotation = 180f
            }
        }
    }


    private fun updateView() {

        when (personalOrCompany) {
            PERSONAL -> {
                when (viewType) {
                    BANKS_LOANS -> {
                        with(binding) {
                            toolbar.title.text = "قروض أفراد"
                            personalLoans1.text = "قرض افراد 1"
                            personalLoans2.text = "قرض افراد 2"
                            personalLoans3.text = "قرض افراد 3"
                        }
                    }
                    ISLAMIC_FINANCING -> {
                        with(binding) {
                            toolbar.title.text = "تمويلات أفراد"
                            personalLoans1.text = "تمويل افراد 1"
                            personalLoans2.text = "تمويل افراد 2"
                            personalLoans3.text = "تمويل افراد 3"
                        }
                    }
                }
            }

            COMPANY->{
                when (viewType) {
                    BANKS_LOANS -> {
                        with(binding) {
                            toolbar.title.text = "قروض شركات"
                            personalLoans1.text = "قرض شركات 1"
                            personalLoans2.text = "قرض شركات 2"
                            personalLoans3.text = "قرض شركات 3"
                        }
                    }
                    ISLAMIC_FINANCING -> {
                        with(binding) {
                            toolbar.title.text = "تمويلات شركات"
                            personalLoans1.text = "تمويل شركات 1"
                            personalLoans2.text = "تمويل شركات 2"
                            personalLoans3.text = "تمويل شركات 3"
                        }
                    }
                }
            }
        }
    }


}