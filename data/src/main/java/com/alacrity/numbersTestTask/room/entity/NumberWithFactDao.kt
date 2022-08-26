package com.alacrity.numbersTestTask.room.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.alacrity.numbersTestTask.entity.NumberWithFact

@Dao
interface NumberWithFactDao {

        @Query("SELECT * FROM numbersWithFacts")
        fun getAll(): List<NumberWithFactTableItem>

        @Insert
        fun insertAll(vararg numbersWithFact: NumberWithFactTableItem)

        @Delete
        fun delete(numberWithFact: NumberWithFactTableItem)


}