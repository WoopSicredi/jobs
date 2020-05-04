package com.example.appteste.view.register

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appteste.EventListActivity
import com.example.appteste.R
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {

    companion object {
        const val NAME_KEY = "nameKey"
        const val EMAIL_KEY = "emailKey"
    }

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = getSharedPreferences("com.example.appTeste", Context.MODE_PRIVATE)
        if (hasRegistration()) {
            navigateToEventList()
        }
        setContentView(R.layout.activity_register)
        setupRegisterButton()
    }

    private fun hasRegistration(): Boolean {
        return (prefs.getString(NAME_KEY, "") ?: "").isNotBlank()
                && (prefs.getString(EMAIL_KEY, "") ?: "").isNotBlank()
    }

    private fun navigateToEventList() {
        val eventListIntent = Intent(this, EventListActivity::class.java)
        startActivity(eventListIntent)
        finishAffinity()
    }

    private fun setupRegisterButton() {
        save_name_button.setOnClickListener {
            val name = name.text.toString()
            val email = email.text.toString()

            prefs.edit().putString(NAME_KEY, name).apply()
            prefs.edit().putString(EMAIL_KEY, email).apply()

            navigateToEventList()
        }
    }
}
