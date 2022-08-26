package com.alacrity.numbersTestTask.utils

import java.lang.StringBuilder

fun String.getFirstNumberFromString(): Int? {
    val num = StringBuilder()
    forEach {
        if(it.isDigit())
            num.append(it)
    }
    return try {
        num.toString().toInt()
    } catch (e: Exception) {
        null
    }
}