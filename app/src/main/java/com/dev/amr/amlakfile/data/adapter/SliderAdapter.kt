package com.dev.amr.amlakfile.data.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.amr.amlakfile.data.model.model.Slid
import com.dev.amr.amlakfile.databinding.SliderRowLayoutBinding

class SliderAdapter(context: Context, list: List<Slid>) :
    RecyclerView.Adapter<SliderAdapter.SliderVH>() {

    private lateinit var binding: SliderRowLayoutBinding
    private val listImg = list
    private val iContext = context
    private lateinit var intent : Intent

    class SliderVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderVH {
        binding = SliderRowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SliderVH(binding.root)
    }

    override fun getItemCount(): Int {
        return listImg.size
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: SliderVH, position: Int) {
        val imgList = listImg[position]
        binding.txtTitleStory.text = imgList.title
        binding.imgSlid.background = iContext.resources.getDrawable(imgList.image)
        binding.imgSlid.setOnClickListener {
            when(position){
                0 ->{
                    intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://my.ssaa.ir/portal/ssar/originality-document"))
                    iContext.startActivity(intent)
                }
                1 ->{
                    intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://price.iranamlaak.ir"))
                    iContext.startActivity(intent)
                }
                2 ->{
                    intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cbi.ir/simplelist/19689.aspx"))
                    iContext.startActivity(intent)
                }
                3 ->{
                    intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://epostcode.post.ir/user/certification"))
                    iContext.startActivity(intent)
                }
            }
        }
    }
}