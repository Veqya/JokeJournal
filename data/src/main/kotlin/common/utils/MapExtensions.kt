package common.utils

import entities.remote.ActionResult
import retrofit2.Response

fun <T> mapResponse(response: Response<T>): ActionResult<T> {
    return when {
        response.isSuccessful -> ActionResult.Success(response.body()!!)
        else -> ActionResult.Error(
            "Server Error: ${response.errorBody()?.string()}",
            response.body()
        )
    }
}