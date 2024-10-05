//package com.example.data
//
//import com.example.domain.ApiResult
//import com.example.domain.customExeption.ConnectionError
//import com.example.domain.customExeption.ServerError
//import com.example.domain.models.ErrorResponse
//import com.google.gson.Gson
//import okio.IOException
//import retrofit2.HttpException
//import java.util.concurrent.TimeoutException
//
//suspend fun <T> executeApi(api: suspend () -> T): ApiResult<T> {
//    return try {
//        ApiResult.Success(data = api.invoke())
//        ApiResult.Loading(isLoading = false)
//    } catch (ex: Exception) {
//        when (ex) {
//            is HttpException -> {
//                val serverResponse = ex.response()?.errorBody()?.string()
//                val error = Gson().fromJson(serverResponse, ErrorResponse::class.java)
//                return ApiResult.Failure(
//                    ServerError(
//                        serverMessage = error.message,
//                        statusMsg = error.status
//                    )
//                )
//            }
//            is IOException, is TimeoutException -> {
//                return ApiResult.Failure(ConnectionError())
//            }
//            else -> return ApiResult.Failure(ex)
//        }
//    }
//}