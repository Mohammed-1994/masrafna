package com.example.masrafna.ui.navigation.banks

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masrafna.data.models.BankModel
import com.example.masrafna.databinding.BankItemBinding
import com.example.masrafna.databinding.BankServiceItemBinding
import com.example.masrafna.ui.services.localization.LocalizationListAdapter

class BankServicesAdapter(val context: Context, private val bankClicked: OnBankClicked) :
    RecyclerView.Adapter<BankServicesAdapter.BankServiceViewHolder>() {

    var banks = ArrayList<BankModel>()


    inner class BankServiceViewHolder(val binding: BankServiceItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankServiceViewHolder {
        val view = BankServiceItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return BankServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: BankServiceViewHolder, position: Int) {
        val currentBank = banks[position]
        with(holder) {
            binding.image.setImageResource(currentBank.image)
            binding.title.text = currentBank.title

            binding.root.setOnClickListener {
                bankClicked.onClick(currentBank)
            }
        }
    }

    override fun getItemCount(): Int {
        return banks.size
    }

    fun submitBanks(services: ArrayList<BankModel>) {
        this.banks = services
        notifyDataSetChanged()
    }

    interface OnBankClicked{
        fun  onClick(bank: BankModel)
    }


}