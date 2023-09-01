package com.jokejournal.android.ui.jokes.list

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
    }

    private fun initJokesList() = with(viewBinding.fragmentJokesList) {
        layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )
        itemAnimator = DefaultItemAnimator()
        adapter = jokeListAdapter

    }
}