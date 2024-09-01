package com.example.moneyminder

import java.text.SimpleDateFormat
import java.util.Locale

object Utils {

    fun formatDate(dateInMillis:Long):String{
        val dateFormatter = SimpleDateFormat("dd/MM/YYYY", Locale.getDefault())
        return dateFormatter.format(dateInMillis)
    }
}