package com.example.news.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.ApiResult
import com.example.domain.models.LNews
import com.example.domain.useCases.InsertNewsUseCase
import com.example.news.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetsilsViewModel @Inject constructor(private val insertNewsUseCase: InsertNewsUseCase) : BaseViewModel() {

    private val _insertNews = MutableLiveData<ApiResult<LNews?>>()
    val insertNews get() = _insertNews

    fun deleteNews(lNews: LNews) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val response = insertNewsUseCase.invoke(lNews)
                _insertNews.postValue(response)
            }
        }catch (e : Exception){
            handleError(e)
        }
    }
}