package com.example.news.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.ApiResult
import com.example.domain.models.LNews
import com.example.domain.models.ModelNewsSource
import com.example.domain.useCases.GetLatestNewsUseCase
import com.example.domain.useCases.GetNewsBysourceUseCase
import com.example.domain.useCases.GetNewsSourceUseCase
import com.example.news.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatestNewsUseCase: GetLatestNewsUseCase,
    private val getNewsSourceUseCase: GetNewsSourceUseCase,
    private val getNewsBysourceUseCase: GetNewsBysourceUseCase
) : BaseViewModel() {

    private val _lNews = MutableLiveData<List<LNews>?>()
    val lNews get() = _lNews
    private val _newsSource = MutableLiveData<List<ModelNewsSource>?>()
    val newsSource get() = _newsSource
    private val _newsBySource = MutableLiveData<List<LNews>?>()
    val newsBySource get() = _newsBySource

    fun getLatestNews(title: String) {
        showLoading()
            viewModelScope.launch(Dispatchers.IO) {
                val response = getLatestNewsUseCase.invoke(title)
                when (response) {
                    is ApiResult.Failure -> handleError(response.throwable)
                    is ApiResult.Success -> _lNews.postValue(response.data)
                }
            }
    }

    fun getNewsSource(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getNewsSourceUseCase.invoke(category)
            when(response){
                is ApiResult.Failure -> handleError(response.throwable)
                is ApiResult.Success -> _newsSource.postValue(response.data)
            }
        }
    }

    fun getNewsBySource(source: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getNewsBysourceUseCase.invoke(source)
            when(response){
                is ApiResult.Failure -> handleError(response.throwable)
                is ApiResult.Success -> _newsBySource.postValue(response.data)
            }
        }
    }
}