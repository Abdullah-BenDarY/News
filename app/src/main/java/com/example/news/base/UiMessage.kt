package com.example.news.base

data class UIMessage (
    val showLoading:Boolean? = null,
    val showMessage:Boolean?=null,

    val message:String? = null,
    val messageId:Int? = null,

    val posButtonId:Int? = null,
    val posButtonText:String? = null,
    val onPosClick: (() -> Unit)?= null,

    val negButtonId:Int? = null,
    val negButtonText:String? = null,
    val onNegClick: (() -> Unit)? = null,

    val isCancelable:Boolean = true,

    )