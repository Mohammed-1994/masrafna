package com.example.masrafna.ui.navigation.profile

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.navigation.fragment.findNavController
import com.example.masrafna.R
import com.example.masrafna.databinding.FragmentProfileBinding
import com.example.masrafna.ui.navigation.NavigationDrawerActivity
import java.util.*

private const val TAG = "ProfileFragment myTag"

class ProfileFragment : Fragment() {
    private var name = ""
    private var password = ""
    private var phoneNO = ""
    private var dOB = ""

    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        getData()
        setupSpinner()
        setTextFields()
    }

    private fun setupToolbar() {

        with(binding) {
            toolbar.drawerIcon.setOnClickListener {
                (requireContext() as NavigationDrawerActivity)
                    .binding.drawerLayout.open()
            }
            toolbar.navigateUp.setOnClickListener {
                findNavController().navigateUp()
            }

            if (!resources.getBoolean(R.bool.is_right_to_left)) {
                toolbar.navigateUp.rotation = 180f
            }
        }
    }

    /**
     * request data from the server and populate it to ui.
     */
    private fun getData() {
        name = binding.nameEt.text.toString()
        password = binding.passwordEt.text.toString()
        phoneNO = binding.phoneNoEt.text.toString()
        dOB = binding.dobEt.text.toString()

    }

    /**
     * set listeners to text fields for enable editing and FAB for save changes
     */
    private fun setTextFields() {


        binding.editDobBtn.setOnClickListener {
            editDOB()
            binding.saveChangesBtn.visibility = VISIBLE
        }
        binding.editNameBtn.setOnClickListener {
            binding.nameEt.isEnabled = true
            binding.saveChangesBtn.visibility = VISIBLE
        }
        binding.editPasswordBtn.setOnClickListener {
            binding.passwordEt.isEnabled = true
            binding.saveChangesBtn.visibility = VISIBLE
        }
        binding.editPhoneBtn.setOnClickListener {
            binding.phoneNoEt.isEnabled = true
            binding.saveChangesBtn.visibility = VISIBLE
        }

        binding.saveChangesBtn.setOnClickListener {
            checkChangesAndSave()
        }
    }


    /**
     * show date picker dialog for change the date of birth of the user
     */
    private fun editDOB() {

        val c = Calendar.getInstance();
        val mYear = c.get(Calendar.YEAR);
        val mMonth = c.get(Calendar.MONTH);
        val mDay = c.get(Calendar.DAY_OF_MONTH);

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { view, year, month, dayOfMonth ->

                binding.dobEt.setText("$dayOfMonth-${month + 1}-$year");

            }, mYear, mMonth, mDay
        );
        datePickerDialog.show();
        //txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
    }

    /**
     * check if any changes happened for data and if so, update the user profile.
     * otherwise ignore.
     *
     */
    private fun checkChangesAndSave() {
        val newName = binding.nameEt.text.toString()
        val newPassword = binding.passwordEt.text.toString()
        val newPhoneNO = binding.phoneNoEt.text.toString()
        val newDOB = binding.dobEt.text.toString()

        if (name != newName || password != newPassword || phoneNO != newPhoneNO || dOB != newDOB) {
            savData(newName, newPhoneNO, newDOB, newPassword)
        }
    }

    /**
     * update user`s data to the server
     */
    private fun savData(newName: String, newPhoneNO: String, newDOB: String, newPassword: String) {

        Log.d(TAG, "savData: \n$newName \n$newPhoneNO \n$newPassword \n$newDOB")
        onDataSaved()
    }

    /**
     * listener for completed updating to populate the new data to ui.
     * also disable editing in the text fields.
     */
    private fun onDataSaved() {
        binding.saveChangesBtn.visibility = GONE
        binding.phoneNoEt.isEnabled = false
        binding.nameEt.isEnabled = false
        binding.passwordEt.isEnabled = false
        binding.dobEt.isEnabled = false
    }


    /**
     * setup the spinner to show user`s city and get all cities from the server.
     * show all cities to the spinner.
     */
    private fun setupSpinner() {
        val spinner = binding.citySpinner

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.planets_array,
            R.layout.my_spinner_background
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.my_spinner_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }

}