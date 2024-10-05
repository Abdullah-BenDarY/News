package com.example.news.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.LNews
import com.example.domain.models.ModelNewsSource
import com.example.news.R
import com.example.news.databinding.ItemCategoriesBinding

class AdapterSourceTabs() : RecyclerView.Adapter<AdapterSourceTabs.Holder>() {

   private var sourcesList : List<ModelNewsSource> ? = null
    private var selectedPossision = 0
    private lateinit var onClick: (ModelNewsSource) -> Unit?
    fun setOnClick(onClick: (ModelNewsSource) -> Unit) {
        this.onClick = onClick}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int = sourcesList?.size ?: 0

    override fun onBindViewHolder(holder: Holder, @SuppressLint("RecyclerView") position: Int) {
        val category = sourcesList!![position]
        holder.bind(category, position == selectedPossision)
        holder.binding.btnTabs.setOnClickListener {
            onClick.invoke(category)
            if (position != selectedPossision) {
                val previousPossition = selectedPossision
                selectedPossision = position
                notifyItemChanged(previousPossition)
                notifyItemChanged(position)
            }
        }
    }

    inner class Holder(val binding: ItemCategoriesBinding) : RecyclerView.ViewHolder(binding.root) {
        private val red = ContextCompat.getColorStateList(binding.root.context, R.color.primary_red)
        private val white = ContextCompat.getColorStateList(binding.root.context, R.color.white)
        private val black = ContextCompat.getColorStateList(binding.root.context, R.color.black)

        fun bind(tabsList: ModelNewsSource, isSelected: Boolean) {
            if (isSelected) {
                binding.btnTabs.setTextColor(white)
                binding.btnTabs.backgroundTintList = red
            } else {
                binding.btnTabs.setTextColor(black)
                binding.btnTabs.backgroundTintList = white
            }
            binding.btnTabs.text = tabsList.name
        }
    }

    fun submitList(sources: List<ModelNewsSource>?) {
        sourcesList = sources
        notifyDataSetChanged()
    }
}