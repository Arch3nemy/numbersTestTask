package com.alacrity.numbersTestTask.room.entity

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NumberWithFactTableItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun numberWithFactDao(): NumberWithFactDao

}