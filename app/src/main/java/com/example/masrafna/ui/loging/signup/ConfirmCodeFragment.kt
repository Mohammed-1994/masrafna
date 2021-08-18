package com.example.masrafna.ui.loging.signup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.masrafna.R
import com.example.masrafna.api.MainAPIClient
import com.example.masrafna.data.auth.request.VerifyOTPModel
import com.example.masrafna.data.auth.response.VerifyOTPResponse
import com.example.masrafna.databinding.FragmentConfirmCodeBinding
import com.example.masrafna.util.Session
import com.google.android.material.snackbar.Snackbar
import io.reactivex.schedulers.Schedulers
import android.view.View.GONE
import android.view.View.VISIBLE
import retrofit2.HttpException


private const val TAG = "ConfirmCodeFragment myTag"

class ConfirmCodeFragment : Fragment() {
    private val mainInterface = MainAPIClient.getClient()
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
        with(binding) {

            createBtn.setOnClickListener {
                if (!codeEt.text.toString().trim().isEmpty())
                    checkTheCode(codeEt.text.toString().trim())
                else
                    Snackbar.make(
                        binding.root,
                        "تأكد من ادخال الرمز",
                        Snackbar.LENGTH_LONG
                    ).show()
            }

            sendAgainBtn.setOnClickListener {
                requestCodeAgain()
            }
        }
    }


    /**
     * checks the code if its correct comparing with the server side code.
     */
    private fun checkTheCode(code: String) {
        binding.progressBar.visibility = VISIBLE
        mainInterface.verifyOTP(VerifyOTPModel(code))
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    activity?.runOnUiThread {
                        binding.progressBar.visibility = GONE
                    }
                    if (it.error) {
                        Snackbar.make(
                            binding.root,
                            it.message, Snackbar.LENGTH_LONG
                        )
                            .show()

                        Log.e(TAG, "checkTheCode: error ${it.message}")
                    } else
                        verifiedSuccessful(it)
                },
                {
                    activity?.runOnUiThread {
                        binding.progressBar.visibility = GONE
                    }
                    Log.e(TAG, "checkTheCode: Error", it)
                    if (it is HttpException){
                        if(it.code() == 422){
                            Snackbar.make(
                                binding.root,
                                "الرجاء التأكد من الرمز المرسل", Snackbar.LENGTH_LONG
                            )
                                .show()
                        }

                    }
                })
        // assume the code is correct

    }

    private fun verifiedSuccessful(verifyOTPResponse: VerifyOTPResponse) {
        Session.verifyOTPResponse = verifyOTPResponse
        activity?.runOnUiThread {
            findNavController().navigate(R.id.action_nav_confirmCodeFragment_to_nav_welcomeFragment)
        }
    }


    /**
     * request to send another code from the server after check any conditions
     *
     */
    private fun requestCodeAgain() {
        binding.progressBar.visibility = VISIBLE
        mainInterface.resendOTPCode()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    activity?.runOnUiThread {
                        binding.progressBar.visibility = GONE
                    }
                    if (it.error) {
                        Snackbar.make(
                            binding.root,
                            it.message, Snackbar.LENGTH_LONG
                        )
                            .show()

                        Log.e(TAG, "checkTheCode: error ${it.message}")
                    } else {
                        Snackbar.make(
                            binding.root,
                            "تم ارسال الكود من جديد", Snackbar.LENGTH_LONG
                        )
                            .show()

                    }
                },
                {
                    activity?.runOnUiThread {
                        binding.progressBar.visibility = GONE
                    }
                    Log.e(TAG, "requestCodeAgain: Error", it)
                    if (it is HttpException){
                        if(it.code() == 422){
                            Snackbar.make(
                                binding.root,
                                "الرجاء الانتظار دقيقة بعد اخر طلب", Snackbar.LENGTH_LONG
                            )
                                .show()
                        }

                    }
                })
    }


}