package com.example.news.ui.details

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.domain.models.LNews
import com.example.news.R
import com.example.news.base.BaseFragment
import com.example.news.databinding.FragmentDetailsBinding
import com.example.news.utils.hideBottomNav
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding, DetailsViewModel>() {

    private val _viewModel: DetailsViewModel by viewModels()
    private val args by navArgs<DetailsFragmentArgs>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsBinding {
        return FragmentDetailsBinding.inflate(inflater, container, false)
    }

    override fun initViewModel(): DetailsViewModel {
        return _viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.NewsDetails?.let { newsDetails -> setViews(newsDetails)}
        handleFavoritesData()
        initClicks()
        hideBottomNav()
    }

    private fun initClicks() {
        binding.tvTitle.setOnClickListener {
            intentBrowser()
        }
    }

    private fun intentBrowser() {
        val url = args.NewsDetails?.url ?: return
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun setViews(newsDetails: LNews) {
        binding.apply {
            Glide.with(requireContext())
                .load(newsDetails.urlToImage)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ivNews)

            collapsingToolBar.title = newsDetails.author
            tvTitle.text = newsDetails.title
            tvDescription.text = newsDetails.description
            tvContent.text = newsDetails.content
        }
    }

    private var isDataInitialized = false
    private fun handleFavoritesData() {
        if (isDataInitialized) return // Prevent multiple initializations

        val gray = ContextCompat.getColorStateList(binding.root.context, R.color.hint_gray)
        val red = ContextCompat.getColorStateList(binding.root.context, R.color.primary_red)

        _viewModel.checkIfNewsExists(args.NewsDetails?.id!!)
        updateButtonAppearance(binding.btnAddToFav, _viewModel.isNewsSaved, gray!!, red!!)

        binding.btnAddToFav.setOnClickListener {
            if (_viewModel.isNewsSaved) {
                _viewModel.toggleFavoriteStatus(args.NewsDetails!!)
            } else {
                _viewModel.toggleFavoriteStatus(args.NewsDetails!!)
            }
            updateButtonAppearance(binding.btnAddToFav, _viewModel.isNewsSaved, gray, red)
        }

        isDataInitialized = true // Mark data as initialized
    }


    private fun updateButtonAppearance(
        button: ImageView,
        isSaved: Boolean,
        gray: ColorStateList,
        red: ColorStateList
    ) {
        button.imageTintList = if (isSaved) red else gray
    }
}