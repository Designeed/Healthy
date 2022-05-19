package com.example.healthy.domain.util

import java.text.DateFormat

class StringConverter {
    fun changeDateFormat(param: String) : String = DateFormat.getDateInstance().format(DateFormat.getDateInstance().parse(param))
}