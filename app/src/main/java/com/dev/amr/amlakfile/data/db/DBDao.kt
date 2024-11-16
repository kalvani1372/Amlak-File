package com.dev.amr.amlakfile.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dev.amr.amlakfile.data.model.model.RegisterBuyAndSellModelFormOne
import com.dev.amr.amlakfile.data.model.model.RegisterBuyAndSellModelFormTwo
import kotlinx.coroutines.flow.Flow

@Dao
interface DBDao {

    @Insert
    fun upsertRegisterBuyAndSellFormOne(registerBuyAndSellFormOne : RegisterBuyAndSellModelFormOne) : Long

    @Insert
    fun upsertRegisterBuyAndSellFormTow(registerBuyAndSellFormTow : RegisterBuyAndSellModelFormTwo) : Long

    @Delete
    fun deleteRegisterBuyAndSell(registerBuyAndSell : RegisterBuyAndSellModelFormTwo)

    @Query("SELECT * FROM tbl_registerBuyAndSellFormOne")
    fun loadAllRegisterBuyAndSellFormOne() : Flow<List<RegisterBuyAndSellModelFormOne>>

    @Query("SELECT * FROM tbl_registerBuyAndSellFormTow")
    fun loadAllRegisterBuyAndSellFormTow() : Flow<List<RegisterBuyAndSellModelFormTwo>>

}