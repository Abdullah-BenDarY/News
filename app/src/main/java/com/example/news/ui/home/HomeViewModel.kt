package com.example.news.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.LNews
import com.example.domain.models.ModelNewsSource
import com.example.domain.useCases.GetLatestNewsUseCase
import com.example.domain.useCases.GetNewsSourceUseCase
import com.example.news.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatestNewsUseCase: GetLatestNewsUseCase,
    private val getNewsSourceUseCase: GetNewsSourceUseCase
) : BaseViewModel() {

    private val _lNews = MutableLiveData<List<LNews>?>()
    val lNews get() = _lNews
    private val _newsSource = MutableLiveData<List<ModelNewsSource>?>()
    val newsSource get() = _newsSource


    fun getLatestNews(title: String) {
        showLoading()
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val response = getLatestNewsUseCase.invoke(title)
                _lNews.postValue(response)
                hideLoading()
            }
        }catch (e : Exception){
            handleError(e)
        }
    }

    fun getNewsSource(category: String) {
        showLoading()
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val response = getNewsSourceUseCase.invoke(category)
                _newsSource.postValue(response)
                hideLoading()
            }
        } catch (e: Exception) {
            handleError(e)
        }
    }
}