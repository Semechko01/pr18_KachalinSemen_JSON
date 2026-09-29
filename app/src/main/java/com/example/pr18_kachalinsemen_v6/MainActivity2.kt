package com.example.pr18_kachalinsemen_v6

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    private lateinit var TextLogin: TextView
    private lateinit var TextPassword: TextView
    private lateinit var ButtonExit: Button
    private lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
        TextLogin = findViewById(R.id.TextLogin)
        TextPassword = findViewById(R.id.TextPassword)
        ButtonExit = findViewById(R.id.ButnExit)
        TextLogin.text = "Ваш логин: ${prefs.getString("username",null)}"
        TextPassword.text = "Ваш пароль: ${prefs.getString("password",null)}"
        ButtonExit.setOnClickListener{
            prefs.edit()
                .putString("username", null)
                .putString("password", null)
                .putBoolean("is_logged", false)
                .apply()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}