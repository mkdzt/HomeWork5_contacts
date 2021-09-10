package com.example.homework5_contacts.entity

object Contacts {
    val contactList = mutableListOf<Contact>()

    fun createContacts(){
        for (i in 0..150){
            val id = i.toString()
            val name = "name_${i}"
            val surname = "surname_${i}"
            val phone = "${i}-7682301"
            val image = "https://picsum.photos/id/${id}/200"
            val contact = Contact(id, name, surname, phone, image)
            contactList.add(contact)
        }
    }
}