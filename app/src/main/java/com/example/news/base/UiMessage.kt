package com.example.e_commerce_route_c40.base

data class UIMessage (
    val showLoading:Boolean? = null,
    val showMessage:Boolean?=null,

    val message:String? = null,
    val messageId:Int? = null,

    val posButtonId:Int? = null,
    val posButtonText:String? = null,
    val onPosClick: OnDialogClick? = null,

    val negButtonId:Int? = null,
    val negButtonText:String? = null,
    val onNegClick: OnDialogClick? = null,

    val isCancelable:Boolean = true,

    )

fun interface OnDialogClick {
    fun onClick()
}