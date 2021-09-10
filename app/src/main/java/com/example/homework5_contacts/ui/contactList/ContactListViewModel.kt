package com.example.homework5_contacts.ui.contactList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homework5_contacts.entity.Contact
import com.example.homework5_contacts.entity.Contacts

class ContactListViewModel:ViewModel() {

    private val _contacts: MutableLiveData<ArrayList<Contact>> = MutableLiveData(Contacts.contactList)
    val contacts:LiveData<ArrayList<Contact>> = _contacts

    fun deleteContact(contact: Contact){
        _contacts.value?.remove(contact)
    }
}