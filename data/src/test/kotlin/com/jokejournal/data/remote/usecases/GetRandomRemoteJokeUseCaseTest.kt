package com.jokejournal.data.remote.usecases

import entities.remote.ActionResult
import entities.remote.Joke
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import repositories.JokesRepository
import usecases.GetRandomRemoteJokeUseCaseImpl

@RunWith(MockitoJUnitRunner::class)
class GetRandomRemoteJokeUseCaseTest {

    @Mock
    lateinit var jokesRepository: JokesRepository

    private lateinit var getRandomRemoteJokeUseCase: GetRandomRemoteJokeUseCaseImpl

    @Before
    fun setUp() {
        getRandomRemoteJokeUseCase = GetRandomRemoteJokeUseCaseImpl(jokesRepository, Dispatchers.IO)
    }

    @Test
    fun `execute returns joke when repository returns joke`() = runBlocking {
        val expectedJoke = Joke(1, "Test Type", "Test Setup", "Test Punchline")
        val expectedActionResult = ActionResult.Success(expectedJoke)

        Mockito.`when`(jokesRepository.getRandomRemoteJoke()).thenReturn(expectedActionResult)

        val result = getRandomRemoteJokeUseCase.execute()

        assertEquals(expectedActionResult, result)
    }

    @Test
    fun `execute returns error when repository returns error`() = runBlocking {
        val error = Throwable("Test error")
        val expectedActionResult = ActionResult.Error<Joke>(error.message.toString())

        Mockito.`when`(jokesRepository.getRandomRemoteJoke()).thenReturn(expectedActionResult)

        val result = getRandomRemoteJokeUseCase.execute()

        assertEquals(expectedActionResult, result)
    }
}