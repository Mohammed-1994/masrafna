package com.example.masrafna.ui.loging.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.masrafna.R
import com.example.masrafna.api.MainAPIClient
import com.example.masrafna.data.auth.request.LoginModel
import com.example.masrafna.data.auth.response.LoginResponse
import com.example.masrafna.databinding.FragmentLoginBinding
import com.example.masrafna.ui.navigation.NavigationDrawerActivity
import com.example.masrafna.util.Session
import com.google.android.material.snackbar.Snackbar
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

private const val TAG = "LoginFragment myTag"

class LoginFragment : Fragment() {
    private val mainInterface = MainAPIClient.getClient()
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
            it.findNavController().navigate(R.id.nav_signup)
        }

        checkAuth()
        binding.loginBtn.setOnClickListener {
            if (checkInputs()) {
                login()
            } else
                Snackbar.make(binding.root, "الرجاء التحقق من المدخلات", Snackbar.LENGTH_LONG)
                    .show()

        }

        binding.forgotPassword.setOnClickListener {

            it.findNavController().navigate(R.id.action_nav_login_to_resetPasswordFragment)

        }


    }

    private fun login() {
        binding.progressBar.visibility = VISIBLE
        val loginModel = LoginModel(
            password = binding.passwordEt.text.toString().trim(),
            phone = binding.phoneNoEt.text.toString().trim()
        )
        mainInterface.login(loginModel)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    activity?.runOnUiThread {
                        binding.progressBar.visibility = View.GONE
                    }
                    if (it.error) {
                        Snackbar.make(
                            binding.root,
                            it.message, Snackbar.LENGTH_LONG
                        )
                            .show()
                    } else
                        loginSuccessful(it)
                },
                {
                    activity?.runOnUiThread {
                        binding.progressBar.visibility = View.GONE
                    }
                    Log.e(TAG, "checkTheCode: Error", it)
                    if (it is HttpException) {
                        if (it.code() == 422) {
                            Snackbar.make(
                                binding.root,
                                "الرجاء التأكد من رقم الهاتف وكلمة المرور", Snackbar.LENGTH_LONG
                            )
                                .show()
                        }

                    }
                })
    }

    private fun loginSuccessful(response: LoginResponse?) {
        with(Session) {
            if (response != null) {
                token = response.payload.accessToken.token
                loginResponse = response
            }
        }

        val sharedPref = activity?.getSharedPreferences(
            getString(R.string.access_token_preferences), Context.MODE_PRIVATE
        ) ?: return
        with(sharedPref.edit()) {
            putString(getString(R.string.access_token), response!!.payload.accessToken.token)
            apply()
        }
        requireContext().startActivity(
            Intent(requireContext(), NavigationDrawerActivity::class.java)
        )
        requireActivity().finish()
    }

    private fun checkAuth() {

        val sharedPref = activity?.getSharedPreferences(
            getString(R.string.access_token_preferences), Context.MODE_PRIVATE
        ) ?: return

        val token = sharedPref.getString(getString(R.string.access_token), null)
        if (token != null) {
            startActivity(Intent(requireContext(), NavigationDrawerActivity::class.java))
            requireActivity().finish()

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