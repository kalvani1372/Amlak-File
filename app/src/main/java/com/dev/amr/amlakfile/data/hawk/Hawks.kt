package com.dev.amr.amlakfile.data.hawk

import com.orhanobut.hawk.Hawk

// todo Hawk manager class
object Hawks {
    var KEY_NAME = "name"
    var KEY_FAMILY = "family"
    var KEY_PHONE_NUMBER = "phone_number"
    var KEY_USERNAME = "username"
    var KEY_PROVINCE = "province"
    var KEY_CITY = "city"
    var KEY_NAME_AMLAK = "name_amlak"
    var KEY_VERSION_APP = "version_app"

    // todo Strings
    fun save(key: String?, value: String) {
        Hawk.put(key, value)
    }

    fun getString(key: String?): String {
        return Hawk.get(key, "")
    }

    fun getString(key: String?, defValue: String): String {
        return Hawk.get(key, defValue)
    }

    // todo int
    fun save(key: String?, value: Int) {
        Hawk.put(key, value)
    }

    fun getInt(key: String?): Int {
        return Hawk.get(key, 0)
    }

    fun getInt(key: String?, defValue: Int): Int {
        return Hawk.get(key, defValue)
    }

    // todo boolean
    fun save(key: String?, value: Boolean) {
        Hawk.put(key, value)
    }

    fun getBoolean(key: String?): Boolean {
        return Hawk.get(key, false)
    }

    fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return Hawk.get(key, defValue)
    }

    // todo float
    fun save(key: String?, value: Float) {
        Hawk.put(key, value)
    }

    fun getFloat(key: String?): Float {
        return Hawk.get(key)
    }

    fun getFloat(key: String?, defValue: Float): Float {
        return Hawk.get(key, defValue)
    }

    // todo delete
    fun delete(key: String?) {
        Hawk.delete(key)
    }
}