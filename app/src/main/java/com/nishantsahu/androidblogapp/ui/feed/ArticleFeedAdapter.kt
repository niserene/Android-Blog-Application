package com.nishantsahu.androidblogapp.ui.feed

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.nishantsahu.androidblogapp.databinding.ListItemArticleBinding
import com.nishantsahu.api.models.entities.Article

class ArticleFeedAdapter(var articlesList:ArrayList<Article>): RecyclerView.Adapter<ArticleFeedAdapter.articleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): articleViewHolder {

        val binding = ListItemArticleBinding.inflate(
                parent.context.getSystemService(LayoutInflater::class.java),
                parent,
                false
        )
        return articleViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: articleViewHolder, position: Int) {

        ListItemArticleBinding.bind(holder.itemView).apply {
            val article = articlesList[position]
            authorTextView.text = article.author.username
            titleTextView.text = article.title
        }
    }

    override fun getItemCount() = articlesList.size

    inner class articleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}