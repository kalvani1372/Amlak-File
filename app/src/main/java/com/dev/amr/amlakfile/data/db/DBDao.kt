package com.dev.amr.amlakfile.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dev.amr.amlakfile.data.model.model.RegisterBuyAndSellModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DBDao {

    @Insert
    fun upsertRegisterBuyAndSell(registerBuyAndSell : RegisterBuyAndSellModel) : Long

    @Delete
    fun deleteRegisterBuyAndSell(registerBuyAndSell : RegisterBuyAndSellModel)

    @Query("SELECT * FROM tbl_registerBuyAndSell")
    fun loadAllRegisterBuyAndSell() : Flow<List<RegisterBuyAndSellModel>>

}