package com.jokejournal.data.local.usecases

import entities.remote.Joke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import repositories.JokesRepository
import usecases.DeleteLocalJokeUseCase
import usecases.DeleteLocalJokeUseCaseImpl

@RunWith(MockitoJUnitRunner::class)
class DeleteLocalJokeUseCaseTest {

    @Mock
    lateinit var jokesRepository: JokesRepository

    private lateinit var deleteLocalJokeUseCase: DeleteLocalJokeUseCase

    @Before
    fun setUp() {
        deleteLocalJokeUseCase = DeleteLocalJokeUseCaseImpl(jokesRepository, Dispatchers.IO)
    }

    @Test
    fun `execute calls repository's delete method with correct joke`() = runBlocking {
        val jokeToDelete = Joke(1, "Test Type", "Test Setup", "Test Punchline")

        deleteLocalJokeUseCase.execute(jokeToDelete)

        verify(jokesRepository).deleteLocalJokeById(jokeToDelete)
    }

    @Test(expected = Throwable::class)
    fun `execute throws error when repository throws error`() = runBlocking {
        val jokeToDelete = Joke(1, "Test Type", "Test Setup", "Test Punchline")
        val error = Throwable("Test error")

        Mockito.doThrow(error).`when`(jokesRepository).deleteLocalJokeById(jokeToDelete)

        deleteLocalJokeUseCase.execute(jokeToDelete)
    }
}
