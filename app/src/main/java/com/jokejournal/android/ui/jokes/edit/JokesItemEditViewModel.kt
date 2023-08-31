package com.jokejournal.android.ui.jokes.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import common.utils.ValueCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import entities.remote.Joke
import kotlinx.coroutines.launch
import usecases.DeleteLocalJokeUseCase
import usecases.EditLocalJokeUseCase
import usecases.GetLocalJokeByIdUseCase
import javax.inject.Inject

@HiltViewModel
class JokesItemEditViewModel @Inject constructor(
    private val deleteLocalJokeUseCase: DeleteLocalJokeUseCase,
    private val editLocalJokeUseCase: EditLocalJokeUseCase,
    private val getLocalJokeByIdUseCase: GetLocalJokeByIdUseCase
) : ViewModel() {
    fun getJokeById(jokeId: Int, callbackJoke: ValueCallback<Joke>) {
        viewModelScope.launch {
            callbackJoke(getLocalJokeByIdUseCase.execute(jokeId))
        }
    }

    fun editLocaleJoke(id: Int, type: String, setup: String, punchline: String) {
        viewModelScope.launch {
            editLocalJokeUseCase.execute(id, type, setup, punchline)
        }
    }

    fun deleteLocalJokeById(jokeId: Int) {
        viewModelScope.launch {
            deleteLocalJokeUseCase.execute(getLocalJokeByIdUseCase.execute(jokeId))
        }
    }
}