package com.alacrity.numbersTestTask.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alacrity.numbersTestTask.entity.NumberWithFact

@Entity(tableName = "numbersWithFacts")
class NumberWithFactTableItem(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "number") val number: Int?,
    @ColumnInfo(name = "fact") val fact: String
)