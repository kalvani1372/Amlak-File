package com.dev.amr.amlakfile.data.model.model

import android.text.InputFilter
import android.text.Spanned

class PersianInputFilter : InputFilter {
    private val persianLettersRegex = Regex("^[\u0600-\u06FF\\s]+$")
    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned,
        dstart: Int, dend: Int): CharSequence? {
        // بررسی اینکه تمام کاراکترهای وارد شده فقط حروف فارسی هستند
        for (i in start until end) {
            if (!persianLettersRegex.matches(source.subSequence(i, i + 1))) {
                return "" // اگر کاراکتر غیر مجاز بود، آن را فیلتر می‌کنیم
            }
        }
        return null // اجازه می‌دهیم ورودی تایید شود
    }
}
