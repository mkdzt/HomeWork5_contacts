package com.example.homework5_contacts.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(val id:String,val name:String,val surname:String,val phone:String):Parcelable
