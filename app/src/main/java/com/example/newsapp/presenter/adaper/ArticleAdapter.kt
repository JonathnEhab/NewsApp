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
import com.example.newsapp.domain.news.Article

class ArticleAdapter(private val onItemClick :(String,String,String,String,String,String)-> Unit) : ListAdapter<Article,
        ArticleAdapter.ViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.from_left)
        holder.itemView.startAnimation(animation)



    }

    inner class ViewHolder(private val itemBinding: ItemNewsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(article: Article) {
            if (article.title == "[Removed]") {
                itemBinding.root.visibility = View.GONE
                itemBinding.root.layoutParams = RecyclerView.LayoutParams(0, 0)
            } else {
                if (article.urlToImage == null) {
                    itemBinding.imageView.visibility = View.GONE
                    itemBinding.noImage.visibility = View.VISIBLE
                    itemBinding.textViewTitle.text = article.title

                } else {
                    itemBinding.textViewTitle.text = article.title
                    Glide.with(itemBinding.root.context).load(article.urlToImage)
                        .into(itemBinding.imageView)
                    itemBinding.root.setOnClickListener {
                         onItemClick(article.title,article.urlToImage,article.description
                         ,article.publishedAt,article.source.name,article.author)

                    }
                }
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