package com.example.news.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.domain.models.LNews
import com.example.news.R
import com.example.news.base.BaseFragment
import com.example.news.base.BaseViewModel
import com.example.news.databinding.FragmentDetailsBinding
import com.example.news.utils.hideBottomNav


class DetailsFragment : BaseFragment<FragmentDetailsBinding, BaseViewModel>() {

    private val _viewModel: BaseViewModel by viewModels()
    private val args by navArgs<DetailsFragmentArgs>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsBinding {
        return FragmentDetailsBinding.inflate(inflater, container, false)
    }

    override fun initViewModel(): BaseViewModel {
        return _viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews(args.NewsDetails)
        initclicks()
        hideBottomNav()
    }

    private fun initclicks() {
        binding.tvTitle.setOnClickListener {
            intentBrowser()
        }
    }

    private fun intentBrowser() {
        val url = args.NewsDetails?.url
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        startActivity(intent)
    }

    private fun setViews(newsDetails: LNews?) {
        binding.apply {
            Glide.with(binding.root.context)
                .load(newsDetails?.urlToImage)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.ivNews)
            collapsingToolBar.title = newsDetails?.author
            tvTitle.text = newsDetails?.title
            tvDescription.text = newsDetails?.description
            tvContent.text = newsDetails?.content
        }
    }
}