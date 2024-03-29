package com.example.newsapp.presenter.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    var _binding : FragmentDetailsBinding?= null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getString("title")
        val urlToImage = arguments?.getString("urlToImage")
        val description = arguments?.getString("description")
        val publishedAt = arguments?.getString("publishedAt")
        val source = arguments?.getString("source")
        val author = arguments?.getString("author")



        Glide.with(requireActivity()).load(urlToImage)
            .into(binding.imageView)
        binding.title.text = title
        binding.description.text = description
        binding.author.text = author
        binding.pushedDate.text = publishedAt
        binding.source.text = source





    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


}