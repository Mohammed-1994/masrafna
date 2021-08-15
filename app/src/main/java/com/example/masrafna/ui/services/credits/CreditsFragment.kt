package com.example.masrafna.ui.services.credits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.masrafna.R
import com.example.masrafna.databinding.FragmentCreditsBinding


class CreditsFragment : Fragment() {

    private lateinit var binding: FragmentCreditsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreditsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.documentaryCredits.setOnClickListener {
            val bundle = bundleOf("view_type" to documentary_credits)
            findNavController().navigate(
                R.id.action_nav_creditFragment_to_nav_creditsTypeFragment,
                bundle
            )
        }

        binding.externalRemittances.setOnClickListener {
            val bundle = bundleOf("view_type" to external_remittances)
            findNavController().navigate(
                R.id.action_nav_creditFragment_to_nav_creditsTypeFragment,
                bundle
            )
        }

    }


    companion object {
        const val documentary_credits = 1
        const val external_remittances = 2
    }

}