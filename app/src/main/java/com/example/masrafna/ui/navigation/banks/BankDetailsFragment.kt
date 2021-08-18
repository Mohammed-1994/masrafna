package com.example.masrafna.ui.navigation.banks

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
import com.example.masrafna.databinding.FragmentBankDetailsBinding
import com.example.masrafna.ui.navigation.NavigationDrawerActivity

class BankDetailsFragment : Fragment(), BankServicesAdapter.OnBankClicked {

    private lateinit var binding: FragmentBankDetailsBinding
    private var mContext: Context? = null
    private var banksList = ArrayList<BankModel>()
    private lateinit var bankServicesAdapter: BankServicesAdapter
    private var title = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = requireArguments()
            .getString("id").toString()
//        findNavController().currentDestination?.label = title
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBankDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mContext = requireContext()
        getBankServices()


        binding.title.text = "${binding.title.text} $title"

        bankServicesAdapter = BankServicesAdapter(mContext!!, this)

        binding.banksRv.apply {
            bankServicesAdapter.submitBanks(banksList)
            adapter = bankServicesAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)

        }
    }

    private fun getBankServices() {


        banksList = arrayListOf(
            BankModel("القروض", R.drawable.bank_financing),
            BankModel("حسابات الودائع", R.drawable.bank_accounts),
            BankModel("البطاقات الالكترونية", R.drawable.bank_cards),
            BankModel("الصرافات الآلية", R.drawable.bank_atm),
            BankModel("نقاط البيع", R.drawable.banks_sell_points),
            BankModel("فروع المصرف", R.drawable.banks_branches),
            BankModel("الخدمات الأخرى", R.drawable.banks_others)
        )


    }

    override fun onClick(bank: BankModel) {
        val id = bundleOf(
            "id" to bank.title
        )

//        findNavController().navigate(R.id.action_to_localization_bank_fragment, id)
    }


}