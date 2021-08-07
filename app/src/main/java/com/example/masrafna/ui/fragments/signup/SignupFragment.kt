package com.example.masrafna.ui.fragments.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.masrafna.R
import com.example.masrafna.databinding.FragmentSignupBinding
import com.google.android.material.snackbar.Snackbar

private const val TAG = "SignupFragment myTag"

class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.createBtn.setOnClickListener {
            if (chekInputs())
                it.findNavController().navigate(R.id.confirmCodeFragment)

        }
    }

    /**
     * checks if the inputs are not empty
     * and the tow passwords are the same
     */
    private fun chekInputs(): Boolean {
        if (
            binding.firstNameEt.text.toString().isEmpty() ||
            binding.lastNameEt.text.toString().isEmpty() ||
            binding.phoneNoEt.text.toString().isEmpty() ||
            binding.passwordEt.text.toString().isEmpty() ||
            binding.confirmPasswordEt.text.toString().isEmpty()
        ) {
            Snackbar.make(binding.root, "الرجاء التحقق من المدخلات", Snackbar.LENGTH_LONG)
                .show()
            return false
        }

        if (binding.passwordEt.text.toString() != binding.confirmPasswordEt.text.toString()) {

            Snackbar.make(binding.root, "الرجاء التحقق من تطابق كلمتي المرور", Snackbar.LENGTH_LONG)
                .show()
            return false
        }
        if (!binding.agreeCheck.isChecked){
            Snackbar.make(binding.root, "الرجاء الموافقة على الشروط", Snackbar.LENGTH_LONG)
                .show()
            return false

        }


        return true
    }


}