package com.example.news.ui.details

import androidx.lifecycle.viewModelScope
import com.example.domain.models.LNews
import com.example.domain.useCases.CheckInsertedNewsUseCase
import com.example.domain.useCases.DeleteNewsUseCase
import com.example.domain.useCases.InsertNewsUseCase
import com.example.news.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val insertNewsUseCase: InsertNewsUseCase,
    private val deleteNewsUseCase: DeleteNewsUseCase,
    private val checkInsertedNewsUseCase: CheckInsertedNewsUseCase
) : BaseViewModel() {
    private var _isNewsSaved: Boolean = false
    val isNewsSaved get() = _isNewsSaved

     fun checkIfNewsExists(id: Int) {
         viewModelScope.launch {
             val isSaved = checkInsertedNewsUseCase.invoke(id)
             _isNewsSaved = isSaved
         }
    }

    fun toggleFavoriteStatus(news: LNews) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_isNewsSaved) {
                deleteNewsUseCase.invoke(news)
                _isNewsSaved = false
            } else {
                insertNewsUseCase.invoke(news)
                _isNewsSaved = true
            }
        }
    }

}