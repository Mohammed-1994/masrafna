package com.example.masrafna.ui.services.localization

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.masrafna.R
import com.example.masrafna.data.models.BankModel
import com.example.masrafna.databinding.FragmentLocalizationBinding
import com.example.masrafna.ui.navigation.NavigationDrawerActivity
import com.example.masrafna.util.NoOfColumns


private const val TAG = "LocalizationFragment myTag"

class LocalizationFragment : Fragment(), LocalizationListAdapter.OnBankClicked {

    private lateinit var binding: FragmentLocalizationBinding
    private lateinit var localizationListAdapter: LocalizationListAdapter
    private var mContext: Context? = null
    private var banksList = ArrayList<BankModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocalizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext = requireContext()

        setupToolbar()
        getBanks()

        localizationListAdapter = LocalizationListAdapter(mContext!!, this)

        binding.banksRv.apply {
            localizationListAdapter.submitBanks(banksList)
            adapter = localizationListAdapter
          layoutManager = GridLayoutManager(requireContext(), 2)

        }
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
            toolbar.title.visibility = GONE

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

    override fun onClick(bank: BankModel) {
        val id = bundleOf(
            "id" to bank.title
        )

        findNavController().navigate(R.id.action_to_localization_bank_fragment, id)
    }


}