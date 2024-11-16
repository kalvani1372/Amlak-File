package com.dev.amr.amlakfile.data.model.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_registerBuyAndSellFormOne")
data class RegisterBuyAndSellModelFormOne (
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    // todo step one
    val userRegistering: String,
    val dateRegistering: String,
    val timeRegistering: String,

    // todo step two
    val ownerName: String,
    val ownerFamily: String,
    val ownerPhone: String,

    // todo step three
    val priceMelk: String,

    // todo step four
    val addressFile: String,
    val metrazhMoraba: String,

    // todo step five
    val sureVame: String,
    val description: String
)