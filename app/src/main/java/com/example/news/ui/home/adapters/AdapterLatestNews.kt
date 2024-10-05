package com.example.news.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.LNews
import com.example.domain.models.ModelNewsSource
import com.example.news.databinding.ItemLatestNewsBinding

class AdapterLatestNews: RecyclerView.Adapter<AdapterLatestNews.Holder>() {
   private var lNewsList: List<LNews>? = null

    private lateinit var onClick: (LNews) -> Unit?
    fun setOnClick(onClick: (LNews) -> Unit) {
        this.onClick = onClick}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemLatestNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int =
        lNewsList?.size ?: 0


    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = lNewsList!![position]
        holder.bind(data)
        holder.binding.root.setOnClickListener {
            onClick.invoke(data)
        }
    }


    inner class Holder(val binding: ItemLatestNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LNews) {

            binding.tvAuther.text = item.author
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description
            Glide
                .with(binding.root.context)
                .load(item.urlToImage)
                .into(binding.ivNewsBg)
        }
    }
    fun submitList(sources: List<LNews>?) {
        lNewsList = sources
        notifyDataSetChanged()
    }

}