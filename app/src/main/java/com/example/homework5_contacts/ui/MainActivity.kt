package com.example.homework5_contacts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework5_contacts.R
import com.example.homework5_contacts.entity.Contacts

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Contacts.createContacts()
        setTheme(R.style.Theme_HomeWork5_contacts)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}