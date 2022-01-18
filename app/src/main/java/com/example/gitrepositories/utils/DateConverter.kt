package com.example.gitrepositories.utils

import java.text.SimpleDateFormat
import java.util.*

class DateConverter {

    fun converterDate(date: String) : String {

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-DD", Locale.getDefault())

        val dateParse = simpleDateFormat.parse(date)

        simpleDateFormat.applyPattern("DD/MM/yy")

        return simpleDateFormat.format(dateParse)
    }
}