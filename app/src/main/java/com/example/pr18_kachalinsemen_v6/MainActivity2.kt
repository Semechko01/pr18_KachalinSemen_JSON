package com.example.pr18_kachalinsemen_v6

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class MainActivity2 : AppCompatActivity() {
    private lateinit var TextLogin: TextView
    private lateinit var TextPassword: TextView
    private lateinit var ButtonExit: Button
    private lateinit var prefs: SharedPreferences
    private lateinit var EditName: EditText
    private lateinit var EditPhone: EditText
    private lateinit var EditEmail: EditText
    private lateinit var SaveButton: Button
    private lateinit var ScroolView: LinearLayout
    var sw: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        prefs = getSharedPreferences("user2", Context.MODE_PRIVATE)
        ScroolView = findViewById(R.id.container)
        TextLogin = findViewById(R.id.TextLogin)
        TextPassword = findViewById(R.id.TextPassword)
        ButtonExit = findViewById(R.id.ButnExit)
        EditName = findViewById(R.id.EditName)
        EditPhone = findViewById(R.id.EditPhone)
        EditEmail = findViewById(R.id.EditEmail)
        SaveButton = findViewById(R.id.Save)
        SaveButton.setOnClickListener {
            if (EditName.text.toString().length == 0 || EditPhone.text.toString().length == 0 || EditEmail.text.toString().length == 0) {
                showAlert("Пустые строки")
                return@setOnClickListener
            }
            val book = Contact(
                name = EditName.text.toString(),
                number = EditPhone.text.toString().toLong(),
                email = EditEmail.text.toString()
            )
            if (sw.contains(book.name))
            {
                showAlert("Такое имя уже есть")
                return@setOnClickListener
            }

            sw += "Name: ${book.name} ,";
            sw += "Number: ${book.number} ,";
            sw += "Email: ${book.email} ,";
            EditEmail.setText("")
            EditPhone.setText("")
            EditName.setText("")
            addTextView("Name: ${book.name}");
            addTextView("Number: ${book.number}");
            addTextView("Email: ${book.email}");
            prefs.edit().putString("books",sw).apply()
        }
        TextLogin.text = "Ваш логин: ${prefs.getString("username",null)}"
        TextPassword.text = "Ваш пароль: ${prefs.getString("password",null)}"
        val tags = prefs.getString("books",null).toString()
            .split(",")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
        for(s in tags)
        {
            addTextView(s);
        }
        ButtonExit.setOnClickListener{
            prefs.edit()
                .putString("username", null)
                .putString("password", null)
                .putBoolean("is_logged", false)
                .putString("books", "")
                .apply()
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
    private fun addTextView(text: String) {
        val tv = TextView(this).apply {
            this.text = text
            textSize = 16f
            setTextColor(Color.BLACK)
            setPadding(0, 12, 0, 12)
        }
        ScroolView.addView(tv)
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