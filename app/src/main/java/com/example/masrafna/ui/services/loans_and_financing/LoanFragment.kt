package com.example.masrafna.ui.services.loans_and_financing

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.masrafna.R
import com.example.masrafna.data.models.BankModel
import com.example.masrafna.databinding.FragmentLoanBinding
import com.example.masrafna.ui.navigation.NavigationDrawerActivity
import com.example.masrafna.ui.services.localization.LocalizationListAdapter

class LoanFragment : Fragment() {

    private lateinit var binding: FragmentLoanBinding
    private lateinit var localizationListAdapter: LocalizationListAdapter
    private var mContext: Context? = null
    private var banksList = ArrayList<BankModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mContext = requireContext()
        getBanks()
        setupToolbar()

        val title = requireArguments().getString("loan_id")
        binding.loanTitle.text = title

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
            toolbar.title.visibility = View.GONE

            if (!resources.getBoolean(R.bool.is_right_to_left)) {
                toolbar.navigateUp.rotation = 180f
            }
        }
    }

    private fun getBanks() {


        banksList = arrayListOf(

            BankModel(
                "مصرف التجارة",
                R.drawable.bank_image
            ),
            BankModel(
                "مصرف الرافدين", R.drawable.bank_image2
            ),
            BankModel(
                "مصرف التجارة", R.drawable.bank_image
            ),
            BankModel(
                "مصرف الرافدين", R.drawable.bank_image2
            ),
            BankModel(
                "مصرف التجارة", R.drawable.bank_image
            ),
            BankModel(
                "مصرف الرافدين", R.drawable.bank_image2
            ),
            BankModel(
                "مصرف التجارة", R.drawable.bank_image

            ),
            BankModel(
                "مصرف الرافدين", R.drawable.bank_image2
            ),
            BankModel(
                "مصرف التجارة", R.drawable.bank_image
            )

        )


    }




}