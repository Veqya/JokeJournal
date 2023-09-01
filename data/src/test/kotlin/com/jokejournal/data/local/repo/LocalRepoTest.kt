package com.jokejournal.data.local.repo

import entities.remote.Joke
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
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

@RunWith(MockitoJUnitRunner::class)
class LocalRepoTest {
    @Mock
    lateinit var jokesDao: JokesDao

    @Mock
    lateinit var jokesApi: JokesApi

    private lateinit var jokesRepository: JokesRepository

    @Before
    fun setUp() {
        jokesRepository = JokesRepositoryImpl(Dispatchers.IO, jokesDao, jokesApi)
    }


    @Test
    fun testGetJokeById() {
        runBlocking {
            val jokeId = 1
            val expectedJoke = Joke(jokeId, "Test Type", "Test Setup", "Test Punchline")
            Mockito.`when`(jokesDao.getJokeById(jokeId)).thenReturn(expectedJoke.toLocal())

            val result = jokesRepository.getLocalJokeById(jokeId)

            assertEquals(expectedJoke, result)
            verify(jokesDao).getJokeById(jokeId)
        }
    }
}