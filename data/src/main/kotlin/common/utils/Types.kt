package common.utils

import entities.remote.ActionResult
import retrofit2.Response
import java.io.IOException

typealias ValueCallback<T> = (value: T) -> Unit

suspend fun <T> safeApiCall(call: suspend () -> Response<T>): ActionResult<T> {
    return try {
        val response = call()
        mapResponse(response)
    } catch (e: IOException) {
        ActionResult.Failure("Network failure. Please check your connection.")
    } catch (e: Throwable) {
        ActionResult.Failure(e.localizedMessage ?: "Unknown error")
    }
}