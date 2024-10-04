package com.example.news.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.customExeption.ConnectionError
import com.example.domain.models.LNews
import com.example.domain.useCases.GetLatestNewsUseCase
import com.example.news.base.BaseViewModel
import com.example.news.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getLatestNewsUseCase: GetLatestNewsUseCase) : BaseViewModel() {

    private val _lNews = MutableLiveData<ApiResult<List<LNews>?>>()
    val lNews get() = _lNews

    fun getLatestNews(country: String) {
        showLoading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getLatestNewsUseCase.invoke(country)
                _lNews.postValue(ApiResult.Success(response))
            } catch (e: ConnectionError) {
                // Handle IOException (like no internet or cached data not available)
                _lNews.postValue(ApiResult.Failure(e))
                handleError(e)
//                handleError(e)
            } catch (e: Exception) {
                // Handle other possible exceptions
                _lNews.postValue(ApiResult.Failure(e))
                handleError(e)
            }
        }
    }
}
