package com.jokejournal.android.ui.jokes.list

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.jokejournal.android.R
import com.jokejournal.android.common.base.BaseFragment
import com.jokejournal.android.common.base.BindingInitializer
import com.jokejournal.android.databinding.FragmentJokesListBinding
import com.jokejournal.android.ui.jokes.list.adapter.JokesListAdapter
import dagger.hilt.android.AndroidEntryPoint
import entities.remote.CommonState

@AndroidEntryPoint
class JokesListFragment : BaseFragment<FragmentJokesListBinding, JokesListViewModel>() {
    override val bindingInitializer: BindingInitializer = FragmentJokesListBinding::inflate
    override val viewModel: JokesListViewModel by hiltNavGraphViewModels(R.id.jokes_nav)
    private val jokeListAdapter: JokesListAdapter by lazy {
        JokesListAdapter { joke ->
            navController.navigate(
                JokesListFragmentDirections.actionJokesListFragmentToJokesItemEditFragment(
                    joke.id
                )
            )
        }
    }

    override fun initViews(): Unit = with(viewBinding) {
        initJokesList()
        fragmentJokesListFetchJoke.setOnClickListener {
            viewModel.fetchRandomJokes()
        }
    }

    override fun initCollectors(): Unit = with(viewModel) {
        collectWithLifecycle(localJokes) { jokes ->
            viewBinding.fragmentJokesListNoJokes.isVisible = jokes?.isEmpty() == true
            jokeListAdapter.submitList(jokes)
        }

        collectWithLifecycle(state) { state ->
            when (state) {
                CommonState.NORMAL -> {
                    viewBinding.fragmentJokesListProgress.isVisible = false
                }

                CommonState.PROGRESS -> {
                    viewBinding.fragmentJokesListProgress.isVisible = true
                }

                CommonState.NO_CONNECTION -> {
                    // some checking in feature
                }

                else -> throw IllegalStateException("Unknown state Other Profile: $state")
            }
        }

        collectWithLifecycle(error) { error ->
            error?.message?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                viewModel.resetError()
            }
        }
    }

    private fun initJokesList() = with(viewBinding.fragmentJokesList) {
        layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )
        itemAnimator = DefaultItemAnimator()
        adapter = jokeListAdapter

    }
}