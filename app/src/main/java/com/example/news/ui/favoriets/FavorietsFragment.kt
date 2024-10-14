package com.example.news.ui.favoriets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.news.base.BaseFragment
import com.example.news.databinding.FragmentFavorietsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavorietsFragment : BaseFragment<FragmentFavorietsBinding, FavorietsViewModel>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )
    = FragmentFavorietsBinding.inflate(inflater,container,false)

    private val _viewModel : FavorietsViewModel by viewModels ()

    override fun initViewModel(): FavorietsViewModel {
        return _viewModel
    }

}