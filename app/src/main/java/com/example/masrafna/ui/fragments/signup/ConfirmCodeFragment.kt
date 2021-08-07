package com.example.masrafna.ui.fragments.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.masrafna.R
import com.example.masrafna.databinding.FragmentConfirmCodeBinding
import com.google.android.material.snackbar.Snackbar

class ConfirmCodeFragment : Fragment() {

    private lateinit var binding: FragmentConfirmCodeBinding
    private var code = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfirmCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sendAgainBtn.setOnClickListener {
            requestCodeAgain()
        }

        binding.createBtn.setOnClickListener {
            if (binding.codeEt.text.toString().isNotEmpty()) {
                code = binding.codeEt.text.toString()
                checkTheCode(code)
            }
        }
    }


    /**
     * checks the code if its correct comparing with the server side code.
     */
    private fun checkTheCode(code: String) {
        // make request for check.

        // assume the code is correct
        login()
    }

    private fun login() {
        Snackbar.make(binding.root, "تم التسجيل بنجاح", Snackbar.LENGTH_LONG)
            .show()
        findNavController().navigate(R.id.welcomeFragment)
    }

    /**
     * request to send another code from the server after check any conditions
     *
     */
    private fun requestCodeAgain() {
        Snackbar.make(binding.root, "ارسال الكود مرة أخرى", Snackbar.LENGTH_LONG)
            .show()
    }


}