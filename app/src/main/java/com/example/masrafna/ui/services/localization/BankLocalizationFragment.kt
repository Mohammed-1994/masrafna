package com.example.masrafna.ui.services.localization

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.masrafna.R
import com.example.masrafna.databinding.FragmentBankLocalizationBinding

private const val TAG = "BankLocalizationFragmen myTag"
class BankLocalizationFragment : Fragment() {


    private lateinit var binding: FragmentBankLocalizationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBankLocalizationBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated: ${requireArguments().getString("id")}")
    }


}