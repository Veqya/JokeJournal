package com.jokejournal.data.local.usecases

import entities.remote.Joke
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.fail
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
import usecases.GetLocalJokeByIdUseCase
import usecases.GetLocalJokeByIdUseCaseImpl

@RunWith(MockitoJUnitRunner::class)
class GetLocalJokeByIdUseCaseTest {

    @Mock
    lateinit var jokesRepository: JokesRepository

    private lateinit var getLocalJokeByIdUseCaseImpl: GetLocalJokeByIdUseCase

    @Before
    fun setUp() {
        getLocalJokeByIdUseCaseImpl = GetLocalJokeByIdUseCaseImpl(jokesRepository, Dispatchers.IO)
    }

    @Test
    fun `execute returns joke from repository`(): Unit = runBlocking {
        val jokeId = 1
        val expectedJoke = Joke(jokeId, "Test Type", "Test Setup", "Test Punchline")
        Mockito.`when`(jokesRepository.getLocalJokeById(jokeId)).thenReturn(expectedJoke)

        val result = getLocalJokeByIdUseCaseImpl.execute(jokeId)

        assertEquals(expectedJoke, result)
        verify(jokesRepository).getLocalJokeById(jokeId)
    }

    @Test
    fun `execute throws error when repository throws error`() {
        runBlocking {
            val jokeId = 1
            val errorMessage = "Test error"
            val error = RuntimeException(errorMessage)
            Mockito.doThrow(error).`when`(jokesRepository).getLocalJokeById(jokeId)

            try {
                getLocalJokeByIdUseCaseImpl.execute(jokeId)
                fail("Expected an exception to be thrown")
            } catch (e: Exception) {
                assertEquals(errorMessage, e.message)
            }
        }
    }
}
