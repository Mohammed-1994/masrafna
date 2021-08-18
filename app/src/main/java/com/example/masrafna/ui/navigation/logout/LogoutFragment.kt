package com.example.masrafna.ui.navigation.logout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.masrafna.R
import com.example.masrafna.api.MainAPIClient
import com.example.masrafna.databinding.FragmentLogoutBinding
import com.example.masrafna.ui.loging.LoggingActivity
import com.example.masrafna.ui.navigation.NavigationDrawerActivity
import io.reactivex.schedulers.Schedulers

class LogoutFragment : Fragment() {
    private val mainInterface = MainAPIClient.getClient()
    private lateinit var binding: FragmentLogoutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cancelLogout.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.confirmLogout.setOnClickListener {
            binding.progressBar.visibility = VISIBLE
            logout()
        }
    }

    private fun logout() {
        mainInterface.logout()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    clearToken()
                },
                {
                    clearToken()
                })
    }

    private fun clearToken() {

        val sharedPref = activity?.getSharedPreferences(
            getString(R.string.access_token_preferences), Context.MODE_PRIVATE
        ) ?: return

        val token = sharedPref.getString(getString(R.string.access_token), null)
        if (token != null) {
            sharedPref.edit().remove(getString(R.string.access_token)).apply()
        }

        requireContext().startActivity(
            Intent(requireContext(), LoggingActivity::class.java)
        )
        activity?.runOnUiThread {
            binding.progressBar.visibility = GONE

        }
        requireActivity().finish()

    }


}