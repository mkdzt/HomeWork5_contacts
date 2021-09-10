package com.example.homework5_contacts.ui.contactList

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework5_contacts.R
import com.example.homework5_contacts.adapter.ContactListAdapter
import com.example.homework5_contacts.entity.Contact
import com.example.homework5_contacts.entity.Contacts

class ContactListFragment:Fragment(R.layout.fragment_contact_list) {
    private lateinit var svContacts:SearchView
    private lateinit var rvContacts:RecyclerView
    private lateinit var contactListAdapter: ContactListAdapter
    private val contactListViewModel:ContactListViewModel by viewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        svContacts = view.findViewById(R.id.sv_contacts)
        rvContacts = view.findViewById(R.id.rv_contact_list)
        contactListAdapter = ContactListAdapter(
            Contacts.contactList,
            { contact -> onContactClick(contact) },
            { contact -> onContactLongClick(contact)}
        )
        rvContacts.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = contactListAdapter
        }
        contactListViewModel.contacts.observe(viewLifecycleOwner) {
            contactListAdapter.submitList(it)
        }
        search()
    }

    private fun onContactClick(contact: Contact){
        val bundle = bundleOf("contact" to contact)
        view?.findNavController()?.navigate(R.id.action_contactListFragment_to_contactDetailsFragment,bundle)
    }

    private fun search(){
        svContacts.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                contactListAdapter.filter.filter(newText)
                return true
            }

        })
    }

    private fun onContactLongClick(contact:Contact){
        AlertDialog
            .Builder(context)
            .setMessage("Delete this contact?")
            .setPositiveButton("OK") { _ , _ -> contactListViewModel.deleteContact(contact)}
            .setNegativeButton("Cancel", null)
            .show()
    }
}