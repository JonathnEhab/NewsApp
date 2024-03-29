package com.example.newsapp.presenter.adaper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemNewsBinding
import com.example.newsapp.databinding.ItemSourceBinding
import com.example.newsapp.domain.news.Article
import com.example.newsapp.domain.news.Source

class SourceAdapter() : ListAdapter<Article,
        SourceAdapter.ViewHolder>(CategoryDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemSourceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.to_dawn)
        holder.itemView.startAnimation(animation)

    }
    inner class ViewHolder(private val itemBinding: ItemSourceBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(article: Article) {
            if (article.source.id == null ){
                itemBinding.root.visibility=View.GONE
                itemBinding.root.layoutParams = RecyclerView.LayoutParams(0, 0)
            }else{
                itemBinding.sourceTxt.text=article.source.name

            }

        }
    }


    class CategoryDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(
            oldItem: Article,
            newItem: Article
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Article,
            newItem: Article
        ): Boolean {
            return oldItem == newItem
        }
    }





}