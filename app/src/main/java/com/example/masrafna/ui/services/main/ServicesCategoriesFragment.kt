package com.example.masrafna.ui.services.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.masrafna.R
import com.example.masrafna.data.models.ServicesModel
import com.example.masrafna.databinding.FragmentServicesCategoriesBinding
import com.example.masrafna.ui.navigation.services.ServicesGridAdapter


private const val TAG = "ServicesCategoriesFragm myTag"

class ServicesCategoriesFragment : Fragment(), ServicesListAdapter.OnServiceClicked {

    private lateinit var binding: FragmentServicesCategoriesBinding

    private var mContext: Context? = null
    private var servicesList = ArrayList<ServicesModel>()
    private lateinit var servicesListAdapter: ServicesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentServicesCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mContext = requireContext()
        getServicesList()

        servicesListAdapter = ServicesListAdapter(mContext!!, this)
        servicesListAdapter.submitArticles(servicesList)
        binding.servicesRv.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = servicesListAdapter
        }


    }

    private fun getServicesList() {

        servicesList = arrayListOf(

            ServicesModel(
                mContext!!.getString(R.string.localization_id),
                R.drawable.localization,
                "التوطين",
                "تحويل رواتب الموضفين من القطاع الخاص او العام"
            ),
            ServicesModel(
                mContext!!.getString(R.string.loans_and_financing_id),
                R.drawable.loans_and_financing,
                "القروض والتمويلات",
                "تعرف على الشروط والاليات وطرق التسديد "
            ),
            ServicesModel(
                mContext!!.getString(R.string.deposit_accounts_id),
                R.drawable.deposit_accounts,
                "حسابات الودائع",
                "تشمل انواع الحسابات المصرفية ومتطلبات فتح الحساب"
            ),
            ServicesModel(
                mContext!!.getString(R.string.electronic_cards_id),
                R.drawable.electronic_cards,
                "البطاقات الالكترونية",
                "تعرف علي انواع البطاقات ،متطلبات الحصول عليها"
            ),
            ServicesModel(
                mContext!!.getString(R.string.letters_of_guarantee_id),
                R.drawable.letters_of_guarantee,
                "خطابات الضمان",
                "تشمل التعرف على مفهوم خطابات الضمان \n" +
                        "والمصارف التي تقدم هذه الخدمة"
            ),
            ServicesModel(
                mContext!!.getString(R.string.external_funding_icon_id),
                R.drawable.external_funding_icon,
                "التمويلات الخارجية",
                "الاعتمادات"
            ),
            ServicesModel(
                mContext!!.getString(R.string.western_union_id),
                R.drawable.western_union,
                "التحويل عن طريق\n" +
                        "الويسترن يونين",
                ""
            ),
            ServicesModel(
                mContext!!.getString(R.string.online_banking_services_id),
                R.drawable.online_banking_services,
                "خدمات مصرفية عبر الانترنيت",
                ""
            ),
            ServicesModel(
                mContext!!.getString(R.string.other_services_id),
                R.drawable.other_services,
                " خدمات اخرى",
                ""
            )

        )


    }

    override fun onClick(service: ServicesModel) {
        findNavController().navigate(R.id.action_to_localization_fragment)
    }
}