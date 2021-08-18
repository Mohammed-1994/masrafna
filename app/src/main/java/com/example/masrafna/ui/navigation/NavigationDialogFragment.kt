package com.example.masrafna.ui.navigation

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.masrafna.R
import com.example.masrafna.databinding.FragmentNavigationDialogBinding

class NavigationDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentNavigationDialogBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            binding = FragmentNavigationDialogBinding.inflate(layoutInflater)
            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(binding.root)


            // Add action buttons

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}