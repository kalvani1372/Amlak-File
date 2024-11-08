package com.dev.amr.amlakfile.data.model.custom_views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton

@SuppressLint("AppCompatCustomView")
class IButton : Button {

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