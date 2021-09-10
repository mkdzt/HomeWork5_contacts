package com.example.homework5_contacts.ui.contactDetails

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.homework5_contacts.R
import com.example.homework5_contacts.entity.Contact
import com.squareup.picasso.Picasso

class ContactDetailsFragment:Fragment(R.layout.fragment_contact_details) {

    private lateinit var etName:EditText
    private lateinit var etSurname:EditText
    private lateinit var etPhone:EditText
    private lateinit var ivImageView:ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etName = view.findViewById(R.id.et_name)
        etSurname = view.findViewById(R.id.et_surname)
        etPhone = view.findViewById(R.id.et_phone)
        ivImageView = view.findViewById(R.id.iv_contact_image_full)

        val contact = arguments?.getParcelable<Contact>("contact")

        etName.setText(contact?.name)
        etSurname.setText(contact?.surname)
        etPhone.setText(contact?.phone)
        Picasso.get().load(contact?.image).into(ivImageView)
    }
}