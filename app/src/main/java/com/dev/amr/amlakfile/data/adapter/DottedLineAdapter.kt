package com.dev.amr.amlakfile.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.amr.amlakfile.R

class DottedLineAdapter(private val itemCount: Int) : RecyclerView.Adapter<DottedLineAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pointer_height_steps, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // تنظیمات هر آیتم (اگر لازم باشد)
    }

    override fun getItemCount(): Int = itemCount

}
