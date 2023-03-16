package com.example.roomtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomtest.data.Wallet

class WalletListAdapter: ListAdapter<Wallet, WalletListAdapter.WalletViewHolder>(WalletComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        return WalletViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.plus)
    }

    class WalletViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val walletItemView : TextView = itemView.findViewById(R.id.textView)

        fun bind(money: Int) {
            walletItemView.text = money.toString()
        }

        companion object {
            fun create(parent: ViewGroup): WalletViewHolder {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
                return WalletViewHolder(view)
            }
        }
    }


    class WalletComparator : DiffUtil.ItemCallback<Wallet>() {
        override fun areItemsTheSame(oldItem: Wallet, newItem: Wallet): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Wallet, newItem: Wallet): Boolean {
            return oldItem.plus == newItem.plus
        }

    }
}