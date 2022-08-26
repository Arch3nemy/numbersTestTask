package com.alacrity.numbersTestTask.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alacrity.numbersTestTask.entity.NumberWithFact

@Entity(tableName = "numbersWithFacts")
class NumberWithFactTableItem(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "number") val number: Int,
    @ColumnInfo(name = "fact") val fact: String
)


fun NumberWithFactTableItem.toRawItem(): NumberWithFact {
    return NumberWithFact(uid, number, fact)
}

fun List<NumberWithFactTableItem>.toRawItems(): MutableList<NumberWithFact> {
    val result = mutableListOf<NumberWithFact>()
    forEach {
        result.add(it.toRawItem())
    }
    return result
}