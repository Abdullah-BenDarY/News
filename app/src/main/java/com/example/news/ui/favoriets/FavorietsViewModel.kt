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
class FavorietsViewModel @Inject constructor(private val getFavorietsUseCase: GetLatestNewsUseCase ) : BaseViewModel() {

    private val _favoriets = MutableLiveData<List<LNews>?>()
    val favoriets get() = _favoriets as LiveData<List<LNews>?>

    fun getFavoriets(country: String) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val response = getFavorietsUseCase.invoke(country)
//                _favoriets.postValue(response)
            }
        }catch (e : Exception){
            handleError(e)
        }
    }
}