//package com.example.domain
//
//
//sealed interface ApiResult<T>{
//
//    data class Success<T>(val data:T,
//                          val message:String? = null ) : ApiResult<T>
//
//    data class Failure<T>(
//        val exception: Exception
//    ): ApiResult<T>
//
//    data class Loading<T>(val isLoading:Boolean = true): ApiResult<T>
//
//}