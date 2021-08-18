package com.example.masrafna.ui.loging.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.masrafna.R
import com.example.masrafna.api.MainAPIClient
import com.example.masrafna.data.auth.request.ChangPassVerifyOTP
import com.example.masrafna.data.auth.request.ChangPassword
import com.example.masrafna.data.auth.response.ResetPasswordVerifyOTP
import com.example.masrafna.databinding.FragmentResetPasswordBinding
import com.example.masrafna.util.Session
import com.google.android.material.snackbar.Snackbar
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

private const val TAG = "ResetPasswordFragment myTag"

class ResetPasswordFragment : Fragment() {

    private val mainInterface = MainAPIClient.getClient()
    private var phoneNO = ""
    private lateinit var binding: FragmentResetPasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            confirmBtn.setOnClickListener {
                if (confirmBtn.text.toString().isEmpty()) {
                    Snackbar.make(
                        binding.root,
                        "تأكد من ادخال رقم الهاتف",
                        Snackbar.LENGTH_LONG
                    ).show()
                } else {
                    phoneNO = phoneNoEt.text.toString().trim()
                    resetThePassword()
                }

            }

            sendAgainBtn.setOnClickListener {
                resendOTP()
            }
        }
    }

    private fun resendOTP() {
        binding.progressBar.visibility = VISIBLE

        mainInterface.resetPasswordResendVerifyOTP(
            ChangPassword(phoneNO)
        )
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    activity?.runOnUiThread {
                        binding.progressBar.visibility = View.GONE
                    }
                    Log.d(TAG, "resendOTP: ${it.message}")
                    if (it.error) {
                        Snackbar.make(
                            binding.root,
                            it.message, Snackbar.LENGTH_LONG
                        )
                            .show()

                        Log.e(
                            TAG,
                            "resendOTP: error ${it.message}"
                        )
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
                        binding.progressBar.visibility = View.GONE
                    }
                    Log.e(TAG, "requestCodeAgain: Error", it)
                    if (it is HttpException) {
                        if (it.code() == 422) {
                            Snackbar.make(
                                binding.root,
                                "الرجاء الانتظار دقيقة بعد اخر طلب", Snackbar.LENGTH_LONG
                            )
                                .show()
                        }

                    }
                })
    }

    private fun resetThePassword() {
        binding.progressBar.visibility = VISIBLE
        Log.d(TAG, "verifyTheCode: $phoneNO, ${binding.phoneNoEt.text.toString().trim()} ")

        mainInterface.resetPassword(ChangPassword(phoneNO))
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    activity?.runOnUiThread {
                        binding.progressBar.visibility = View.GONE
                    }
                    Log.e(TAG, "resetThePassword: ${it.message}")

                    if (it.error) {
                        Snackbar.make(
                            binding.root,
                            it.message, Snackbar.LENGTH_LONG
                        )
                            .show()

                    } else
                        verifyTheCode()

                },
                {
                    activity?.runOnUiThread {
                        binding.progressBar.visibility = View.GONE
                    }
                    Log.e(TAG, "resetThePassword: Error", it)
                    if (it is HttpException) {
                        if (it.code() == 404) {

                            Snackbar.make(
                                binding.root,
                                "الرقم المدخل غير صحيح", Snackbar.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                })
    }

    private fun verifyTheCode() {
        with(binding) {

            activity?.runOnUiThread {
                countre.visibility = VISIBLE
                phoneNoEt.text.clear()
                phoneNoEt.hint = "أدخل الرمز"
                sendAgainBtn.visibility = VISIBLE
            }

            confirmBtn.setOnClickListener {
                if (phoneNoEt.text.toString().isEmpty()) {
                    Snackbar.make(
                        binding.root,
                        "تأكد من ادخال الرمز المرسل",
                        Snackbar.LENGTH_LONG
                    ).show()
                } else {
                    Log.d(TAG, "verifyTheCode: $phoneNO, ${phoneNoEt.text.toString().trim()} ")

                    mainInterface.resetPasswordVerifyOTP(
                        ChangPassVerifyOTP(phoneNO, phoneNoEt.text.toString().trim())
                    )
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                            {
                                Log.d(TAG, "verifyTheCode: ${it.message}")
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
                                    verifiedSuccessful(it)
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
                                            "الرجاء التأكد من الرمز المرسل", Snackbar.LENGTH_LONG
                                        )
                                            .show()
                                    }

                                }
                            })
                }


            }
        }


    }

    private fun verifiedSuccessful(verifyOTPResponse: ResetPasswordVerifyOTP) {
        Session.token = verifyOTPResponse.payload.accessToken.token
        activity?.runOnUiThread {
            findNavController().navigate(R.id.action_nav_confirmCodeFragment_to_nav_login)
        }
    }
}