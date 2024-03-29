package com.example.newsapp.presenter.ui.home

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.databinding.ItemNewsBinding
import com.example.newsapp.presenter.adaper.ArticleAdapter
import com.example.newsapp.presenter.adaper.SourceAdapter
import com.example.newsapp.presenter.ui.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sourceAdapter= SourceAdapter()
        val articlesAdapter = ArticleAdapter {title, urlToImage,publishedAt, description, suorce ,author  ->

            findNavController().navigate(
                NewsFragmentDirections.actionNewsFragmentToDetailsFragment(
                    title,
                    urlToImage,
                    publishedAt,
                    description,
                    suorce,
                    author
                )
            )
        }
        binding.sourceRecycler.apply {
            layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter=sourceAdapter
        }
        binding.verticalNewsRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = articlesAdapter
        }
        viewModel.fetchArticleNews()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.articleNews.collect { newsModel ->
                newsModel?.let {
                    articlesAdapter.submitList(it.articles)
                    sourceAdapter.submitList(it.articles)

                }
            }
        }




    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }


}