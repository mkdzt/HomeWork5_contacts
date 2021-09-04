package com.example.homework5_contacts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework5_contacts.R
import com.example.homework5_contacts.entity.Contact

class ContactListAdapter(
    private val contacts:List<Contact> = emptyList(),
    private val onItemClicked:(Contact) -> Unit) : ListAdapter<Contact, ViewHolder>(ContactsDiffCallback) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val view = LayoutInflater.from( parent.context)
            .inflate(R.layout.contact_item, parent, false)
        return ViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)
    }

    override fun getItemCount() = contacts.size
}

class ViewHolder(view: View, private val onItemClicked: (Contact) -> Unit) : RecyclerView.ViewHolder(view){
    private val tvName: TextView = view.findViewById(R.id.tv_name)
    private val tvSurname: TextView = view.findViewById(R.id.tv_surname)
    private val tvPhone: TextView = view.findViewById(R.id.tv_phone)
    private var currentContact:Contact? = null

    init {
        itemView.setOnClickListener{
            currentContact?.let{
                onItemClicked(it)
            }
        }
    }

    fun bind(contact:Contact){
        currentContact = contact
        tvName.text = contact.name
        tvSurname.text = contact.surname
        tvPhone.text = contact.phone
    }
}

object ContactsDiffCallback : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.id == newItem.id
    }
}