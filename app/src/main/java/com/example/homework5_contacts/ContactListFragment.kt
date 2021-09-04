package com.example.homework5_contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactListFragment:Fragment(R.layout.fragment_contact_list) {
    private lateinit var rvContacts:RecyclerView
    private lateinit var contactListAdapter: ContactListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvContacts = view.findViewById(R.id.rv_contact_list)
        contactListAdapter = ContactListAdapter(Contacts.list)
        rvContacts.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = contactListAdapter
        }
    }
}