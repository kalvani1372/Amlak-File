package com.dev.amr.amlakfile.data.model.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_registerBuyAndSellFormTow")
data class RegisterBuyAndSellModelFormTwo(
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
    val typeUser: String,
    val typeSanad: String,
    val location: String,
    val tabaghe: String,
    val ageBana: String,
    val vaziyadMelk: String,
    val cunterOtagh: String,
    val nemaSakhteman: String,
    val sureVame: String,
    val description: String,

    // todo step sex
    val counterAllTabaghat: String,
    val counterAllVahedha: String,
    val counterVahedhaDarTabaghe: String,
    val jahatSakhteman: String,
    val jahatVahed: String,
    val jensKaf: String,
    val edtCabinets: String,
    val wc: String,
    val garmayesh: String,
    val sarmayesh: String,

    // todo step seven
    val checkAsansor: String,
    val checkParking: String,
    val checkMelkBasazi: String,
    val checkJakozi: String,
    val checkAnbari: String,
    val checkEstakhr: String,
    val checkBalkon: String,
    val checkAyfonTasviri: String,
    val checkSona: String,
    val checkLabi: String,
    val checkGazRomizi: String,
    val checkSalonVarzeshi: String,
    val checkHod: String,
    val checkSalonEjtemaat: String,
    val checkDarbZedSerghat: String,
    val checkAntenMarkazi: String,
    val checkKomodDivari: String,
    val checkJarobarghiMarkazi: String,
    val checkHayat: String,
    val checkSelectAll: String,

    // todo step eight
//    val images: ArrayList<String>
)