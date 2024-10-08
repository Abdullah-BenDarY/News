package com.example.news.ui.hot_updates

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
class HotUpdatesViewModel @Inject constructor(
    private val getLatestNewsUseCase: GetLatestNewsUseCase,
) : BaseViewModel() {

    private val _lNews = MutableLiveData<List<LNews>?>()
    val lNews get() = _lNews


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
}