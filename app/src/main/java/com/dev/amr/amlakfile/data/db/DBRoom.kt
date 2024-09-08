package com.dev.amr.amlakfile.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dev.amr.amlakfile.data.model.model.RegisterBuyAndSellModel

@Database(entities = [RegisterBuyAndSellModel::class], version = 1, exportSchema = false)
abstract class DBRoom : RoomDatabase() {
    abstract fun dBDao() : DBDao
}