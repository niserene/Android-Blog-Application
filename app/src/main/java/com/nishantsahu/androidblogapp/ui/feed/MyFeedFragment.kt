package com.nishantsahu.androidblogapp.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nishantsahu.androidblogapp.R
import com.nishantsahu.androidblogapp.databinding.FragmentFeedBinding
import com.nishantsahu.api.models.entities.Article

class MyFeedFragment:Fragment() {

    private var binding: FragmentFeedBinding?= null
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
        feedAdapter = ArticleFeedAdapter(articlesList, { openArticle(it)}) { openProfile(it) }

        binding = FragmentFeedBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.feedRview?.layoutManager = LinearLayoutManager(context)
        binding?.feedRview?.adapter = feedAdapter
        viewModel.fetchMyFeed()
        viewModel.feed.observe({ lifecycle }) {
            articlesList.clear()
            articlesList.addAll(it)
            feedAdapter.notifyDataSetChanged()
        }
    }

    fun openArticle(articleId: String){
        findNavController().navigate(
                R.id.my_feed_openArticle,
                bundleOf(
                        Pair(resources.getString(R.string.arg_article_id), articleId)
                )
        )
    }
    fun openProfile(username:String){
        findNavController().navigate(
                R.id.my_feed_openProfile,
                bundleOf(
                        Pair(resources.getString(R.string.arg_username), username)
                )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}