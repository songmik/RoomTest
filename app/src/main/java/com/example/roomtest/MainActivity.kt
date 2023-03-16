package com.example.roomtest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomtest.data.Wallet
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val newWalletActivityRequestCode = 1
    private val walletViewModel : WalletViewModel by viewModels {
        WalletViewModelFactory((application as WalletApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WalletListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        walletViewModel.allWallets.observe(this) { wallet ->
            wallet.let { adapter.submitList(it) }
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, NewWalletActivity::class.java)
            startActivityForResult(intent, newWalletActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWalletActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewWalletActivity.EXTRA_REPLY)?.let { reply,  ->
                val wallet = Wallet(reply.toInt(), reply.toInt())
                walletViewModel.insert(wallet)
            }
        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_SHORT).show()
        }
    }
}