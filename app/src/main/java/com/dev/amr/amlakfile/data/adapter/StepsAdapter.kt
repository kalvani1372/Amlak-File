package com.dev.amr.amlakfile.data.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.amr.amlakfile.data.model.model.Step
import com.dev.amr.amlakfile.databinding.ItemStepBinding

class StepsAdapter(private val steps: List<Step>?) : RecyclerView.Adapter<StepsAdapter.StepViewHolder>() {

    private lateinit var binding : ItemStepBinding
    inner class StepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(step: Step) {
            // Set the name
            binding.stepNameTextView.text = step.name

            // Change color based on completion
            val color = if (step.isCompleted) Color.GREEN else Color.GRAY
            binding.stepIndicator.setBackgroundColor(color)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        binding = ItemStepBinding.inflate(LayoutInflater.from(parent.context))
        return StepViewHolder(parent.rootView)
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        holder.bind(steps!![position])
    }

    override fun getItemCount() = steps!!.size
}