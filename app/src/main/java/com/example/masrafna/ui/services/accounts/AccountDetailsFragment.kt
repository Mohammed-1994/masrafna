package com.example.masrafna.ui.services.accounts

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
import com.example.masrafna.data.models.AccountModel
import com.example.masrafna.data.models.BankModel
import com.example.masrafna.databinding.FragmentAccountDetailsBinding
import com.example.masrafna.ui.navigation.NavigationDrawerActivity
import com.example.masrafna.ui.services.localization.LocalizationListAdapter
import com.example.masrafna.util.NoOfColumns

class AccountDetailsFragment : Fragment() {

    private lateinit var binding: FragmentAccountDetailsBinding
    private lateinit var localizationListAdapter: LocalizationListAdapter
    private lateinit var accountModel: AccountModel
    private var mContext: Context? = null
    private var banksList = ArrayList<BankModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext = requireContext()
        getBanks()

        setupToolbar()

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
                "???????? ??????????????",
                R.drawable.bank_image
            ),
            BankModel(
                "???????? ????????????????", R.drawable.bank_image2
            ),
            BankModel(
                "???????? ??????????????", R.drawable.bank_image
            ),
            BankModel(
                "???????? ????????????????", R.drawable.bank_image2
            ),
            BankModel(
                "???????? ??????????????", R.drawable.bank_image
            ),
            BankModel(
                "???????? ????????????????", R.drawable.bank_image2
            ),
            BankModel(
                "???????? ??????????????", R.drawable.bank_image

            ),
            BankModel(
                "???????? ????????????????", R.drawable.bank_image2
            ),
            BankModel(
                "???????? ??????????????", R.drawable.bank_image
            )

        )


    }

}