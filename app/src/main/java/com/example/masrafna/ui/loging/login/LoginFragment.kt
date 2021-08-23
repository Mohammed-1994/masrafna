package com.example.masrafna.ui.loging.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.masrafna.R
import com.example.masrafna.api.MainAPIClient
import com.example.masrafna.data.auth.request.LoginBody
import com.example.masrafna.data.auth.response.LoginResponse
import com.example.masrafna.databinding.FragmentLoginBinding
import com.example.masrafna.ui.navigation.NavigationDrawerActivity
import com.example.masrafna.util.Session
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import android.app.DownloadManager
import com.example.masrafna.api.NetworkStatus
import com.example.masrafna.api.Status


private const val TAG = "LoginFragment myTag"

class LoginFragment : Fragment() {


    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel.networkStatus.observe(this, {
            binding.progressBar.visibility =
                if (it == NetworkStatus.LOADING) VISIBLE else GONE

            if (it.status == Status.CODE_422) {
                Snackbar.make(
                    binding.root,
                    it.msg,
                    Snackbar.LENGTH_LONG
                )
                    .show()
            }
        })

        loginViewModel.loginResponse.observe(this, {
            updateUi(it)
        })
    }

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
        val loginBody = LoginBody(
            password = binding.passwordEt.text.toString().trim(),
            phone = binding.phoneNoEt.text.toString().trim()
        )

        loginViewModel.login(loginBody)
    }

    private fun updateUi(response: LoginResponse?) {
        binding.progressBar.visibility = GONE
        if (response != null) {

            with(Session) {


                loginResponse = response

                val sharedPref = activity?.getSharedPreferences(
                    getString(R.string.access_token_preferences), Context.MODE_PRIVATE
                ) ?: return
                with(sharedPref.edit()) {
                    putString(
                        getString(R.string.access_token),
                        response.payload.accessToken.token
                    )
                    apply()
                }
                requireContext().startActivity(
                    Intent(requireContext(), NavigationDrawerActivity::class.java)
                )
                requireActivity().finish()
            }
        }
    }


    private fun checkAuth() {

        val sharedPref = activity?.getSharedPreferences(
            getString(R.string.access_token_preferences), Context.MODE_PRIVATE
        ) ?: return

        val token = sharedPref.getString(getString(R.string.access_token), null)
        Session.token = token.toString()
        val isVerified = sharedPref.getBoolean(getString(R.string.is_account_verified), false)
        if (token != null) {
            Log.d(TAG, "checkAuth: $token")

            if (isVerified) {
                Log.d(TAG, "checkAuth: is verified")
                startActivity(Intent(requireContext(), NavigationDrawerActivity::class.java))
                requireActivity().finish()
            } else {

                Log.d(TAG, "checkAuth: is not verified")
                findNavController().navigate(R.id.action_nav_login_to_nav_confirmCodeFragment)
            }
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