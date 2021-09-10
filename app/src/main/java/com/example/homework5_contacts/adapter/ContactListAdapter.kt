package com.example.homework5_contacts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework5_contacts.R
import com.example.homework5_contacts.entity.Contact
import com.squareup.picasso.Picasso

class ContactListAdapter(
    private val contactList:MutableList<Contact>,
    private val onItemClicked:(Contact) -> Unit,
    private val onLongItemClicked:(Contact) -> Unit) : ListAdapter<Contact, ContactViewHolder>(ContactsDiffCallback()), Filterable {


    private var contactsFilterList: MutableList<Contact> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ContactViewHolder {
        val view = LayoutInflater.from( parent.context)
            .inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(view, onItemClicked, onLongItemClicked)
    }

    override fun onBindViewHolder(holderContact: ContactViewHolder, position: Int) {
        val contact = getItem(position)
        holderContact.bind(contact)
    }

    //очень костылёво конечно, не работает удаление(по-хорошему во viewmodel надо всё красиво делать)
    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(textFilter: CharSequence?): FilterResults {
                if(!textFilter.isNullOrEmpty()){
                    for(contact in contactList){
                        if(contact.name.lowercase().contains(textFilter) || contact.surname.lowercase().contains(textFilter)){
                            contactsFilterList.add(contact)
                        }
                    }
                }else{
                    contactsFilterList = contactList
                }
                val filterResults = FilterResults()
                filterResults.values = contactsFilterList
                return filterResults
            }

            override fun publishResults(textFilter: CharSequence?, filterResults: FilterResults) {
                contactsFilterList = filterResults.values as MutableList<Contact>
                submitList(contactsFilterList)
            }

        }
    }
}

class ContactViewHolder(
    view: View,
    private val onItemClicked: (Contact) -> Unit,
    private val onLongItemClicked: (Contact) -> Unit) : RecyclerView.ViewHolder(view){

    private val tvName: TextView = view.findViewById(R.id.tv_name)
    private val tvSurname: TextView = view.findViewById(R.id.tv_surname)
    private val tvPhone: TextView = view.findViewById(R.id.tv_phone)
    private val ivContactImage: ImageView = view.findViewById(R.id.iv_contact_image)
    private var currentContact:Contact? = null

    init {
        itemView.setOnClickListener{
            currentContact?.let{
                onItemClicked(it)
            }
        }
        itemView.setOnClickListener {
            currentContact?.let {
                onLongItemClicked(it)
            }
        }
    }

    fun bind(contact:Contact){
        currentContact = contact
        tvName.text = contact.name
        tvSurname.text = contact.surname
        tvPhone.text = contact.phone
        Picasso.get().load(contact.image).into(ivContactImage)
    }
}

class ContactsDiffCallback : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }
}