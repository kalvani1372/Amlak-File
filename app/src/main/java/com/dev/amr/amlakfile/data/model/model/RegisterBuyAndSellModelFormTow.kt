package com.dev.amr.amlakfile.data.model.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_registerBuyAndSellFormTow")
data class RegisterBuyAndSellModelFormTow(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val user: String,
    val ownerName: String,
    val ownerPhone: String,
    val edtCabinets: String,
    val addressFile: String,
    val description: String,
    val counterAllTabaghat: String,
    val counterAllVahedha: String,
    val counterVahedhaDarTabaghe: String,
    val jahatSakhteman: String,
    val jahatVahed: String,
    val jensKaf: String,
    val garmayesh: String,
    val sarmayesh: String,
    val typeUser: String,
    val typeSanad: String,
    val metrazhMoraba: String,
    val location: String,
    val ageBana: String,
    val tabaghe: String,
    val cunterOtagh: String,
    val vaziyadMelk: String,
    val sureVame: String,
    val priceMelk: String,
    val wc: String,
    val nemaSakhteman: String,
    val checkAsansor: String,
    val checkShofazh: String,
    val checkAnbari: String,
    val checkParking: String,
    val checkKolerAbi: String,
    val checkFanQoel: String,
    val checkGarmayesh: String,
    val checkKolerGazi: String,
    val checkGazRomizi: String,
    val checkHod: String,
    val checkDarbZedSerghat: String,
    val checkKomodDivari: String,
    val checkSelectAll: String,
    val checkHayat: String,
    val checkJakozi: String,
    val checkLabi: String,
    val checkSalonVarzeshi: String,
    val checkSalonEjtemaat: String,
    val checkAntenMarkazi: String,
    val checkJarobarghiMarkazi: String,
    val image1: String,
    val image2: String,
    val image3: String,
    val image4: String,
    val image5: String,
    val image6: String
)

@Entity(tableName = "tbl_registerBuyAndSellFormOne")
data class RegisterBuyAndSellModelFormOne(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val user: String,
    val ownerName: String,
    val ownerPhone: String,
    val addressFile: String,
    val description: String,
    val sureVame: String,
    val metrazhMoraba: String,
    val priceMelk: String,
)