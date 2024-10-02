package com.example.news.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.LNews
import com.example.domain.useCases.GetLatestNewsUseCase
import com.example.news.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getLatestNewsUseCase: GetLatestNewsUseCase) : BaseViewModel() {

    private val _lNews = MutableLiveData<List<LNews>?>()
    val lNews get() = _lNews as LiveData<List<LNews>?>

    fun getLatestNews(country: String) {
        showLoading()
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val response = getLatestNewsUseCase.invoke(country)
                _lNews.postValue(response)
                hideLoading()
            }
        }catch (e : Exception){
            handleError(e)
        }
    }
}