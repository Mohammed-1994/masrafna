package com.example.masrafna.ui.services.localization

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masrafna.data.models.BankModel
import com.example.masrafna.databinding.BankItemBinding

private const val TAG = "LocalizationListAdapter myTag"
class LocalizationListAdapter(val context: Context, private val bankClicked: OnBankClicked) :
    RecyclerView.Adapter<LocalizationListAdapter.LocalizationViewHolder>() {

    var banks = ArrayList<BankModel>()


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
        val currentBank = banks[position]
        with(holder) {
            binding.image.setImageResource(currentBank.image)
            binding.title.text = currentBank.title

            binding.card.setOnClickListener {
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