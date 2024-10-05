package com.example.news.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.news.ModelTabs
import com.example.news.R
import com.example.news.databinding.ItemCategoriesBinding

class AdapterCategoriesTabs(private var sourcesList: MutableList<ModelTabs>? = mutableListOf()) :
    RecyclerView.Adapter<AdapterCategoriesTabs.Holder>() {

    var onItemClickListener: OnItemClickListener? = null
    private var selectedPossision = 0

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
            onItemClickListener?.onItemClick(category.title ?: "")
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

        fun bind(tabsList: ModelTabs, isSelected: Boolean) {
            if (isSelected) {
                binding.btnTabs.setTextColor(white)
                binding.btnTabs.backgroundTintList = red
            } else {
                binding.btnTabs.setTextColor(black)
                binding.btnTabs.backgroundTintList = white
            }
            binding.btnTabs.text = tabsList.title
        }
    }

    fun submitList(sources: List<ModelTabs>?) {
        sourcesList = sources?.toMutableList() ?: mutableListOf()
        notifyDataSetChanged()
    }

    fun interface OnItemClickListener {
        fun onItemClick(title: String)
    }
}