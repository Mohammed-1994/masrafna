package com.example.masrafna.ui.services.localization

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.masrafna.R
import com.example.masrafna.data.models.BankModel
import com.example.masrafna.data.models.Localizations
import com.example.masrafna.databinding.BankItemBinding
import com.example.masrafna.ui.navigation.banks.BankServicesAdapter
import java.util.ArrayList

private const val TAG = "LocalizationListAdapter myTag"
class LocalizationListAdapter(val context: Context) :
    RecyclerView.Adapter<LocalizationListAdapter.LocalizationViewHolder>() {

    lateinit var banks: MutableList<Localizations.Payload.Data?>


    inner class LocalizationViewHolder(val binding: BankItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalizationViewHolder {
        val view = BankItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LocalizationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocalizationViewHolder, position: Int) {
        with(holder.binding) {
            val currentBank = banks[position]!!
            title.text = currentBank.name

            Glide.with(context)
                .load(currentBank.logo)
                .placeholder(R.drawable.bank_image)
                .into(image)

        }
    }

    override fun getItemCount(): Int {
        return banks.size
    }



}