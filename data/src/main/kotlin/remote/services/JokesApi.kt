package remote.services

import entities.remote.Joke
import retrofit2.Response
import retrofit2.http.GET

interface JokesApi {
    @GET("random_joke")
    suspend fun getRandomJoke(): Response<Joke>
}