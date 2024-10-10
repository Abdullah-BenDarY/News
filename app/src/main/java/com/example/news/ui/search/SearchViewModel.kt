package com.example.news.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.ApiResult
import com.example.domain.models.LNews
import com.example.domain.useCases.GetSearchQueryUseCase
import com.example.news.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchQueryUseCase,
) : BaseViewModel() {

    private val _lSearch = MutableLiveData<List<LNews>?>()
    val lSearch get() = _lSearch


    fun getSearchQuery(query: String) {
        showLoading()
        viewModelScope.launch(Dispatchers.IO) {
            val response = getSearchUseCase.invoke(query)
            when (response) {
                is ApiResult.Failure -> handleError(response.throwable)
                is ApiResult.Success -> _lSearch.postValue(response.data)
            }
        }
    }
}