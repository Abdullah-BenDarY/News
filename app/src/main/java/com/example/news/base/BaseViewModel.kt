package com.example.news.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.customExeption.ConnectionError
import com.example.domain.customExeption.ServerError
import com.example.domain.models.ApiResult
import com.example.news.R


open class BaseViewModel:ViewModel() {
    val uiMessage = MutableLiveData<UIMessage>()


    fun handleError(throwable:Throwable,
                    posActionCallBack: (() -> Unit)?=null){
        if(throwable is ServerError){

            uiMessage.postValue(UIMessage(
                showLoading = false,
                showMessage = true,
                message =  throwable.serverMessage,
                posButtonId = R.string.retry,
                onPosClick = posActionCallBack
            ))
            return
        }

        val message = when(throwable){
            is ConnectionError ->{
                R.string.connection_error
            }
            else->{
                R.string.somethin_went_wrong
            }
        }
        uiMessage.postValue( UIMessage(
            showLoading = false,
            showMessage = true,
            messageId =  message,
            posButtonId = R.string.retry,
            onPosClick = posActionCallBack
        ))
    }

    fun hideLoading(){
        uiMessage.postValue(
            UIMessage(
                showLoading = false)
        )
    }
    fun showLoading(messageId:Int?=null,
                    message:String?=null){
        uiMessage.postValue(UIMessage(
            showLoading = true,
            messageId = messageId,
            message = message
        ))
    }
    fun handleLoading(loading: ApiResult.Loading<*>){
        if(loading.isLoading){
            showLoading(R.string.loading)
            return
        }
        hideLoading()

    }

}