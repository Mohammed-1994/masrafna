package com.example.masrafna.ui.services.others

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ScrollView
import androidx.core.os.bundleOf
import androidx.core.view.iterator
import androidx.navigation.fragment.findNavController
import com.example.masrafna.R
import com.example.masrafna.databinding.FragmentOtherServicesBinding

class OtherServicesFragment : Fragment() {


    private lateinit var binding: FragmentOtherServicesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOtherServicesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.scroll.fullScroll(ScrollView.FOCUS_UP)
        val buttonClicked = View.OnClickListener {
            val title = (it as Button).text
            val bundle = bundleOf("id" to title)
            findNavController().navigate(
                R.id.action_nav_otherServicesFragment_to_nav_banksServiceFragment,
                bundle
            )
        }

        with(binding) {
            for (button in this.servicesList)
                button.setOnClickListener(buttonClicked)
        }
    }


}