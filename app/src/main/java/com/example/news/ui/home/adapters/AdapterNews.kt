package com.example.news.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.LNews
import com.example.news.R
import com.example.news.databinding.ItemNewsBinding

class AdapterNews: RecyclerView.Adapter<AdapterNews.Holder>() {
   private var lNewsList: List<LNews>? = null

    private lateinit var onClick: (LNews) -> Unit?
    fun setOnClick(onClick: (LNews) -> Unit) {
        this.onClick = onClick}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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


    inner class Holder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LNews) {

            binding.tvAuther.text = item.author
            binding.tvTitle.text = item.title
            binding.tvPublishedDate.text = item.publishedAt
            Glide
                .with(binding.root.context)
                .load(item.urlToImage)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.ivNewsBg)
        }
    }
    fun submitList(sources: List<LNews>?) {
        lNewsList = sources
        notifyDataSetChanged()
    }

}