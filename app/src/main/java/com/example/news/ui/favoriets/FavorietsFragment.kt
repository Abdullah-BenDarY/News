package com.example.news.ui.favoriets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.models.LNews
import com.example.news.R
import com.example.news.base.BaseFragment
import com.example.news.databinding.FragmentFavorietsBinding
import com.example.news.ui.search.adapters.AdapterSearch
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavorietsFragment : BaseFragment<FragmentFavorietsBinding, FavorietsViewModel>() {
    private val adapterSearch = AdapterSearch()
    private val _viewModel : FavorietsViewModel by viewModels ()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )
    = FragmentFavorietsBinding.inflate(inflater,container,false)


    override fun initViewModel(): FavorietsViewModel {
        return _viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
        _viewModel.getFavoriets()
        observe()

    }

    private fun initClicks() {
        adapterSearch.setOnClick {
            findNavController().navigate(
                FavorietsFragmentDirections.actionGlobalToDetailsFragment(
                    it
                )
            )
        }
        adapterSearch.setOnLingClick {
            showDialog(
                message = getString(R.string.are_you_sure_to_delete),
                posText = getString(R.string.yes),
                posAction ={deleteItem(it)} ,
                negText = getString(R.string.cancel),
                negAction = {return@showDialog}
            )
        }
    }

    private fun deleteItem(news : LNews){
        _viewModel.deleteNews(news)
        _viewModel.getFavoriets()
    }
    private fun observe(){
        _viewModel.favoriets.observe(viewLifecycleOwner){
            initViews(it)
        }
    }

    private fun initViews(lNews: List<LNews>?) {
        adapterSearch.submitList(lNews)
        binding.rvFav.adapter = adapterSearch
        if(lNews!!.isEmpty()){
            binding.apply {
                rvFav.visibility = View.GONE
                ivPlaceHolder.visibility = View.VISIBLE
                tvPlaceHolder.visibility = View.VISIBLE

            }
        }else binding.apply {
            rvFav.visibility = View.VISIBLE
            ivPlaceHolder.visibility = View.GONE
            tvPlaceHolder.visibility = View.GONE

        }
    }

}