package com.dev.amr.amlakfile.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.base.BaseLiveDialog
import com.dev.amr.amlakfile.data.hawk.Hawks
import com.dev.amr.amlakfile.data.model.custom_views.ITextView
import com.dev.amr.amlakfile.utils.GetListItem

class BtmSheetListAdapter(list: GetListItem) : RecyclerView.Adapter<BtmSheetListAdapter.SVH>() {

    private val strList = list

    class SVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt = itemView.findViewById<ITextView>(R.id.txt)!!
        val lineTop = itemView.findViewById<ConstraintLayout>(R.id.line_top)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SVH {
        val view :View = LayoutInflater.from(parent.context).inflate(R.layout.spinner_row,null)
        return SVH(view)
    }

    override fun getItemCount(): Int {
        return strList.getListItem().size
    }

    override fun onBindViewHolder(holder: SVH, position: Int) {

        val list = strList.getListItem()[position]
        holder.txt.text = list

        holder.txt.setOnClickListener {
            Hawks.save("item",list)
            BaseLiveDialog.liveDataTypeUser.postValue(true)

        }

    }


}