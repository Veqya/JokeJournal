package com.jokejournal.data

import entities.remote.ActionResult
import entities.remote.Joke
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import local.dao.JokesDao
import local.entities.extensions.toLocal
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import remote.services.JokesApi
import repositories.JokesRepository
import repository.JokesRepositoryImpl
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class JokesRepositoryTest {

    @Mock
    lateinit var jokesDao: JokesDao

    @Mock
    lateinit var api: JokesApi

    private lateinit var jokesRepository: JokesRepository

    @Before
    fun setUp() {
        jokesRepository = JokesRepositoryImpl(Dispatchers.IO, jokesDao, api)
    }

    @Test
    fun `getRandomRemoteJoke returns joke from API`(): Unit = runBlocking {
        val jokeResponse = Joke(1, "Test Type", "Test Setup", "Test Punchline")
        Mockito.`when`(api.getRandomJoke()).thenReturn(Response.success(jokeResponse))

        val result = jokesRepository.getRandomRemoteJoke()

        assertTrue(result is ActionResult.Success)
        assertEquals(jokeResponse, (result as ActionResult.Success).data)
    }

    @Test
    fun `saveLocalJoke saves joke to DAO`(): Unit = runBlocking {
        val testJoke = Joke(1, "Test Type", "Test Setup", "Test Punchline")

        jokesRepository.saveLocalJoke(testJoke)

        verify(jokesDao).insertJoke(testJoke.toLocal())
    }

    @Test
    fun `editLocalJoke edits joke in DAO`(): Unit = runBlocking {
        val testJoke = Joke(2, "Edit Type", "Edit Setup", "Edit Punchline")

        jokesRepository.editLocalJoke(testJoke)

        verify(jokesDao).insertJoke(testJoke.toLocal()) // Так как вы используете insert для редактирования
    }

    @Test
    fun `getLocalJokeById returns joke from DAO`(): Unit = runBlocking {
        val jokeId = 3
        val expectedJoke = Joke(jokeId, "Test Type", "Test Setup", "Test Punchline")
        Mockito.`when`(jokesDao.getJokeById(jokeId)).thenReturn(expectedJoke.toLocal())

        val result = jokesRepository.getLocalJokeById(jokeId)

        assertEquals(expectedJoke, result)
    }

    @Test
    fun `getLocalJokes returns list of jokes from DAO`(): Unit = runBlocking {
        val jokesList = listOf(
            Joke(1, "Test Type 1", "Test Setup 1", "Test Punchline 1"),
            Joke(2, "Test Type 2", "Test Setup 2", "Test Punchline 2")
        )
        val jokesFlow = flowOf(jokesList.map { it.toLocal() })
        Mockito.`when`(jokesDao.getLocalJokes()).thenReturn(jokesFlow)

        val result = jokesRepository.getLocalJokes().toList()

        assertEquals(jokesList, result[0])
    }

    @Test
    fun `deleteLocalJokeById deletes joke from DAO`(): Unit = runBlocking {
        val testJoke = Joke(4, "Delete Type", "Delete Setup", "Delete Punchline")

        jokesRepository.deleteLocalJokeById(testJoke)

        verify(jokesDao).deleteJoke(testJoke.toLocal())
    }
}
