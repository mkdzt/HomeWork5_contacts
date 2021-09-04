package com.example.homework5_contacts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homework5_contacts.R
import com.example.homework5_contacts.entity.Contact

class ContactListAdapter(private val contacts:List<Contact>) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvSurname: TextView = view.findViewById(R.id.tv_surname)
        val tvPhone: TextView = view.findViewById(R.id.tv_phone)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from( parent.context)
            .inflate(R.layout.contact_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = contacts[position].name
        holder.tvSurname.text = contacts[position].surname
        holder.tvPhone.text = contacts[position].phone
    }

    override fun getItemCount() = contacts.size
}