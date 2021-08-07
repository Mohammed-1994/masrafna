package com.example.masrafna.ui.fragments.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.masrafna.R
import com.example.masrafna.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

private const val TAG = "LoginFragment myTag"

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createNewAccount.setOnClickListener {
            //navigate to create new account
            it.findNavController().navigate(R.id.navigation_signup)
        }

        binding.loginBtn.setOnClickListener {
            if (checkInputs())
                Snackbar.make(binding.root, "تم تسجيل الدخول", Snackbar.LENGTH_LONG)
                    .show()
            else
                Snackbar.make(binding.root, "الرجاء التحقق من المدخلات", Snackbar.LENGTH_LONG)
                    .show()

        }


    }

    /**
     * checks if the phone number and password are not empty
     */

    private fun checkInputs(): Boolean {
        return !(binding.passwordEt.text.toString().isEmpty()
                || binding.passwordEt.text.toString().isEmpty()
                )

    }


}