package com.nishantsahu.androidblogapp.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nishantsahu.androidblogapp.R
import com.nishantsahu.androidblogapp.databinding.FragmentArticleBinding
import com.nishantsahu.androidblogapp.extensions.timeStamp

class ArticleFragment: Fragment() {

    private var binding:FragmentArticleBinding ?= null
    lateinit var articleViewModel: ArticleViewModel
    private var articleId:String ?= null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentArticleBinding.inflate(inflater, container, false);
        articleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)

        arguments?.let {
            articleId = it.getString(resources.getString(R.string.arg_article_id))
            articleId?.let { articleViewModel.fetchArticle(it) }
            Toast.makeText(context, "Loading article..", Toast.LENGTH_LONG).show()
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleViewModel.article.observe({lifecycle}){
            binding?.apply {
                titleTextView.text = it.title
                bodyTextView.text = it.body
                dateTextView.timeStamp = it.createdAt
                authorTextView.text = it.author.username
//                authorImageView.loadImage(it.author.image)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}