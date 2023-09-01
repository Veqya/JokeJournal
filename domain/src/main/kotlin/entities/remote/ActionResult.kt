package entities.remote

sealed class ActionResult<T> {
    data class Success<T>(val data: T) : ActionResult<T>()
    data class Error<T>(val message: String, val data: T? = null) : ActionResult<T>()
    data class Failure<T>(val message: String) : ActionResult<T>()
}
