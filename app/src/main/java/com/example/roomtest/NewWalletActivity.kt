package com.example.roomtest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewWalletActivity: AppCompatActivity() {

    private lateinit var editWalletView : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_wallet)
        editWalletView = findViewById(R.id.edit_wallet)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()

            if (TextUtils.isEmpty(editWalletView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val wallet = editWalletView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, wallet)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.roomtest."
    }
}