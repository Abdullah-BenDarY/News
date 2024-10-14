package com.example.news.ui.favoriets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.ApiResult
import com.example.domain.models.LNews
import com.example.domain.useCases.DeleteNewsUseCase
import com.example.domain.useCases.GetLatestNewsUseCase
import com.example.domain.useCases.GetOfflineNewsUseCase
import com.example.news.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FavorietsViewModel @Inject constructor(private val getFavorietsUseCase: GetOfflineNewsUseCase,
    private val deleteNewsUseCase: DeleteNewsUseCase) : BaseViewModel() {

    private val _favoriets = MutableLiveData<ApiResult<List<LNews>?>>()
    val favoriets get() = _favoriets

    private val _deleteNews = MutableLiveData<ApiResult<LNews?>>()
    val deleteNews get() = _deleteNews

    fun getFavoriets() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val response = getFavorietsUseCase.invoke()
                _favoriets.postValue(response)
            }
        }catch (e : Exception){
            handleError(e)
        }
    }

    fun deleteNews(lNews: LNews) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val response = deleteNewsUseCase.invoke(lNews)
                _deleteNews.postValue(response)
            }
        }catch (e : Exception){
            handleError(e)
        }
    }
}