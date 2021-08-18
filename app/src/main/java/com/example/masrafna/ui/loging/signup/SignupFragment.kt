package com.example.masrafna.ui.loging.signup

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.masrafna.R
import com.example.masrafna.api.MainAPIClient
import com.example.masrafna.data.auth.request.SignUpModel
import com.example.masrafna.databinding.FragmentSignupBinding
import com.example.masrafna.util.Session
import com.google.android.material.snackbar.Snackbar
import io.reactivex.schedulers.Schedulers

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
                signup()
        }
    }

    //SignupResponse
    private fun signup() {

        with(binding) {
            binding.progressBar.visibility = VISIBLE
            val signUpModel = SignUpModel(
                name = firstNameEt.text.toString().trim() + " " + lastNameEt.text.toString().trim(),
                phone = phoneNoEt.text.toString().trim(),
                password = passwordEt.text.toString().trim()
            )

            val mainInterface = MainAPIClient.getClient()
            mainInterface.signup(signUpModel)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {

                        Log.d(TAG, "signup: success:${it}")
                        Session.signupResponse = it
                        saveToken(it.payload.accessToken.token)
                    }, {

                        activity?.runOnUiThread {
                            binding.progressBar.visibility = GONE
                        }
                        Log.e(TAG, "signup: Error", it)
                    })
        }
    }

    private fun saveToken(accessToken: String) {

        Session.token = accessToken
        val sharedPref = activity?.getSharedPreferences(
            getString(R.string.access_token_preferences), Context.MODE_PRIVATE
        ) ?: return
        with(sharedPref.edit()) {
            putString(getString(R.string.access_token), accessToken)
            apply()
        }

        activity?.runOnUiThread {
            binding.progressBar.visibility = GONE
            findNavController().navigate(R.id.action_nav_signup_to_nav_confirmCodeFragment)
        }
    }


    /**
     * checks if the inputs are not empty
     * and the tow passwords are the same
     */
    private fun chekInputs(): Boolean {
        with(binding) {
            if (
                firstNameEt.text.toString().isEmpty() ||
                lastNameEt.text.toString().isEmpty() ||
                phoneNoEt.text.toString().isEmpty() ||
                passwordEt.text.toString().isEmpty() ||
                confirmPasswordEt.text.toString().isEmpty()
            ) {
                Snackbar.make(root, "الرجاء التحقق من المدخلات", Snackbar.LENGTH_LONG)
                    .show()
                return false
            }

            if (passwordEt.text.toString() != confirmPasswordEt.text.toString()) {

                Snackbar.make(
                    binding.root,
                    "الرجاء التحقق من تطابق كلمتي المرور",
                    Snackbar.LENGTH_LONG
                ).show()
                return false
            } else {
                if (passwordEt.text.toString().trim().length < 8)
                    Snackbar.make(binding.root, "كلمة السر 8 أحرف على الأقل", Snackbar.LENGTH_LONG)
                        .show()
            }

            if (!agreeCheck.isChecked) {
                Snackbar.make(binding.root, "الرجاء الموافقة على الشروط", Snackbar.LENGTH_LONG)
                    .show()
                return false

            }

            return true
        }


    }


}