import com.example.data.api.model.ErrorResponseDTO
import com.example.domain.ApiResult
import com.example.domain.customExeption.ConnectionError
import com.example.domain.customExeption.ServerError
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException

suspend fun <T> executeApi(api: suspend () -> T): ApiResult<T> {
    return try {
        ApiResult.Success(api.invoke())
    } catch (ex: Exception) {
        when (ex) {
            is HttpException -> {
                val errorBody = ex.response()?.errorBody()?.string() ?: ""
                val errorResponse = Gson().fromJson(errorBody, ErrorResponseDTO::class.java)
                ApiResult.Failure(
                    throw ServerError(
                        serverMessage = errorResponse.message,
                        statusMsg = errorResponse.status
                    )
                )
            }

            is SocketTimeoutException -> {
                // Handle timeout specifically
                ApiResult.Failure(throw ConnectionError("Request timed out, please try again."))
            }

            is IOException,
            is TimeoutException -> {
                // Handle network-related issues (no internet, etc.)
                ApiResult.Failure(throw ConnectionError("Network error, please check your internet connection."))
            }

            else -> {
                // Catch any other unexpected exceptions
                ApiResult.Failure(throw ServerError("An unexpected error occurred: ${ex.localizedMessage}"))
            }
        }
    }
}
