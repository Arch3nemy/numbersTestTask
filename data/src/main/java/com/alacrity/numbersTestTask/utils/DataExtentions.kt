package com.alacrity.numbersTestTask.utils

import com.alacrity.numbersTestTask.entity.NumberWithFact
import com.alacrity.numbersTestTask.room.entity.NumberWithFactTableItem

fun NumberWithFact.toTableItem(): NumberWithFactTableItem {
    return NumberWithFactTableItem(uid, number, fact)
}

fun List<NumberWithFact>.toTableItems(): List<NumberWithFactTableItem> {
    val result = mutableListOf<NumberWithFactTableItem>()
    forEach {
        result.add(it.toTableItem())
    }
    return result
}