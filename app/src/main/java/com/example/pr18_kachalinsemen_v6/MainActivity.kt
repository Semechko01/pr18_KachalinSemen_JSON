package com.example.pr18_kachalinsemen_v6

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etUsername = findViewById(R.id.EditLogin)
        etPassword = findViewById(R.id.EditPassword)
        btnLogin = findViewById(R.id.ButtonLogin)
        prefs = getSharedPreferences("user2", Context.MODE_PRIVATE)
        if (prefs.getBoolean("is_logged", false)) {
            startActivity(Intent(this, MainActivity2::class.java))
            return
        }
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username.length == 0 || password.length == 0) {
                showAlert("Логин или пароль пустой")
                return@setOnClickListener
            }

            prefs.edit()
                .putString("username", username)
                .putString("password", password)
                .putBoolean("is_logged", true)
                .putString("books", "")
                .apply()
            startActivity(Intent(this, MainActivity2::class.java))

        }
    }
    private fun showAlert(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Ошибка")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .show()
    }
}