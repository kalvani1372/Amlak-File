package com.dev.amr.amlakfile.data.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.amr.amlakfile.R
import com.dev.amr.amlakfile.data.model.model.Step
import com.dev.amr.amlakfile.databinding.ItemStepBinding

class StepsAdapter(private val steps: List<Step>) : RecyclerView.Adapter<StepsAdapter.StepViewHolder>() {

//    private lateinit var binding : ItemStepBinding
    inner class StepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var stepNameTxt = itemView.findViewById<TextView>(R.id.stepNameTextView)!!
        private var stepIndicator = itemView.findViewById<View>(R.id.stepIndicator)!!
        fun bind(step: Step) {
            stepNameTxt.text = step.name

            val color = if (step.isCompleted) Color.GREEN else Color.GRAY
            stepIndicator.setBackgroundColor(color)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
//        binding = ItemStepBinding.inflate(LayoutInflater.from(parent.context))
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_step,parent,false)
        return StepViewHolder(view)
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        holder.bind(steps[position])
    }

    override fun getItemCount() = steps.size
}