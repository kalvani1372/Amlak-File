package com.dev.amr.amlakfile.data.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.amr.amlakfile.databinding.ImagesRowLayoutBinding

class ImageAdapter(private var list : ArrayList<Image>) : RecyclerView.Adapter<ImageAdapter.ImageVH>() {

    private lateinit var binding : ImagesRowLayoutBinding
    class ImageVH(itemView: View) : RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageVH {
        binding = ImagesRowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImageVH(binding.root)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ImageVH, position: Int) {
    }
}