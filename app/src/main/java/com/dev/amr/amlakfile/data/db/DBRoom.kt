package com.dev.amr.amlakfile.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dev.amr.amlakfile.data.model.model.RegisterBuyAndSellModelFormOne
import com.dev.amr.amlakfile.data.model.model.RegisterBuyAndSellModelFormTwo

@Database(
    entities = [RegisterBuyAndSellModelFormOne::class, RegisterBuyAndSellModelFormTwo::class],
    version = 2,
    exportSchema = false
)

//@Database(entities = [RegisterBuyAndSellModelFormOne::class, RegisterBuyAndSellModelFormTow::class], version = 4)
//@TypeConverters(TypeConvertor::class)
abstract class DBRoom : RoomDatabase() {
    abstract fun dBDao(): DBDao
}