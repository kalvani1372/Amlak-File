package com.dev.amr.amlakfile.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dev.amr.amlakfile.data.model.model.RegisterBuyAndSellModelFormOne
import com.dev.amr.amlakfile.data.model.model.RegisterBuyAndSellModelFormTow

@Database(
    entities = [RegisterBuyAndSellModelFormOne::class, RegisterBuyAndSellModelFormTow::class],
    version = 1,
    exportSchema = false
)

//@Database(entities = [RegisterBuyAndSellModelFormOne::class, RegisterBuyAndSellModelFormTow::class], version = 4)
//@TypeConverters(TypeConvertor::class)
abstract class DBRoom : RoomDatabase() {
    abstract fun dBDao(): DBDao
}