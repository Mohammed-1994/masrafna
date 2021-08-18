package com.example.masrafna.ui.navigation.banks

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.masrafna.R
import com.example.masrafna.data.models.BankModel
import com.example.masrafna.databinding.FragmentBanksListBinding
import com.example.masrafna.ui.services.localization.LocalizationListAdapter

class BanksListFragment : Fragment(), LocalizationListAdapter.OnBankClicked {

    private lateinit var binding: FragmentBanksListBinding
    private lateinit var localizationListAdapter: LocalizationListAdapter
    private var mContext: Context? = null
    private var banksList = ArrayList<BankModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBanksListBinding.inflate(inflater, container, false)
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
                "البنك المركزي ",
                R.drawable.localization
            ),
            BankModel(
                "بنك الرافدين ",
                R.drawable.loans_and_financing
            ),
            BankModel(
                " البنك العراق الوطني",
                R.drawable.deposit_accounts
            ),
            BankModel(
                "البنك العراقي ",
                R.drawable.electronic_cards
            ),
            BankModel(
                " بنك جيهان",
                R.drawable.letters_of_guarantee
            ),
            BankModel(
                "بنك الانتاج",
                R.drawable.external_funding_icon
            ),
            BankModel(
                " بنك التنمية الزراعي",
                R.drawable.western_union

            ),
            BankModel(
                "بنك التنمية الوطني",
                R.drawable.online_banking_services
            ),
            BankModel(
                "بنك الوحدة",
                R.drawable.other_services
            )

        )


    }

    override fun onClick(bank: BankModel) {
        val id = bundleOf(
            "id" to bank.title
        )

        findNavController().graph.findNode(R.id.nav_bankDetailsFragment)
            ?.label = bank.title
        findNavController().navigate(
            R.id.action_nav_banksListFragment_to_nav_bankDetailsFragment, id
        )

    }

}