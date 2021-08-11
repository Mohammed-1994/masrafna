package com.example.masrafna.ui.services.localization

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.masrafna.R
import com.example.masrafna.data.models.BankModel
import com.example.masrafna.databinding.FragmentLocalizationBinding


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
        getBanks()

        localizationListAdapter = LocalizationListAdapter(mContext!!, this)

        binding.banksRv.apply {
            localizationListAdapter.submitBanks(banksList)
            adapter = localizationListAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun getBanks() {


        banksList = arrayListOf(

            BankModel(
                mContext!!.getString(R.string.localization_id),
                R.drawable.localization
            ),
            BankModel(
                mContext!!.getString(R.string.loans_and_financing_id),
                R.drawable.loans_and_financing
            ),
            BankModel(
                mContext!!.getString(R.string.deposit_accounts_id),
                R.drawable.deposit_accounts
            ),
            BankModel(
                mContext!!.getString(R.string.electronic_cards_id),
                R.drawable.electronic_cards
            ),
            BankModel(
                mContext!!.getString(R.string.letters_of_guarantee_id),
                R.drawable.letters_of_guarantee
            ),
            BankModel(
                mContext!!.getString(R.string.external_funding_icon_id),
                R.drawable.external_funding_icon
            ),
            BankModel(
                mContext!!.getString(R.string.western_union_id),
                R.drawable.western_union

            ),
            BankModel(
                mContext!!.getString(R.string.online_banking_services_id),
                R.drawable.online_banking_services
            ),
            BankModel(
                mContext!!.getString(R.string.other_services_id),
                R.drawable.other_services
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