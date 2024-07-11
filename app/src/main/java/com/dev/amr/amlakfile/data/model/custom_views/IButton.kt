package com.dev.amr.amlakfile.data.model.custom_views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class IButton : AppCompatButton {

    constructor(context: Context) : super(context){
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super
        (context, attrs, defStyleAttr){
            initView(context)
        }

    private fun initView(context: Context){
        val typeFace = Typeface.createFromAsset(context.assets,"font/Dirooz.ttf")
        typeface = typeFace
    }

}