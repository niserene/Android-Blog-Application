package com.nishantsahu.androidblogapp.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nishantsahu.androidblogapp.databinding.FragmentFeedBinding
import com.nishantsahu.api.models.entities.Article

class GlobalFeedFragment: Fragment() {

    private var binding: FragmentFeedBinding ?= null
    private lateinit var viewModel: FeedViewModel
    private lateinit var feedAdapter: ArticleFeedAdapter
    private lateinit var articlesList : ArrayList<Article>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        articlesList = ArrayList()
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        feedAdapter = ArticleFeedAdapter(articlesList)
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        binding?.feedRview?.layoutManager = LinearLayoutManager(context)
        binding?.feedRview?.adapter = feedAdapter
        viewModel.fetchGlobalFeed()
        viewModel.feed.observe({ lifecycle }) {
            articlesList.clear()
            articlesList.addAll(it)
            feedAdapter.notifyDataSetChanged()
        }
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}