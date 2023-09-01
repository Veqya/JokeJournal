package com.jokejournal.android.ui.jokes.edit

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jokejournal.android.R
import com.jokejournal.android.common.base.BaseFragment
import com.jokejournal.android.common.base.BindingInitializer
import com.jokejournal.android.databinding.FragmentJokesItemEditBinding
import dagger.hilt.android.AndroidEntryPoint
import entities.remote.CommonState

@AndroidEntryPoint
class JokesItemEditFragment : BaseFragment<FragmentJokesItemEditBinding, JokesItemEditViewModel>() {
    override val bindingInitializer: BindingInitializer = FragmentJokesItemEditBinding::inflate
    override val viewModel: JokesItemEditViewModel by viewModels()
    private val args by navArgs<JokesItemEditFragmentArgs>()
    override fun initViews(): Unit = with(viewBinding) {
        with(fragmentJokesItemEditToolbar) {
            setNavigationOnClickListener { navController.navigateUp() }
            menu.findItem(R.id.jokeEditMenu)
                .setOnMenuItemClickListener {
                    viewModel.editLocaleJoke(
                        id = args.jokeId,
                        type = fragmentJokesItemEditTypeInput.text.toString(),
                        setup = fragmentJokesItemEditSetupInput.text.toString(),
                        punchline = fragmentJokesItemEditPunchlineInput.text.toString()
                    )
                    true
                }
            menu.findItem(R.id.jokeDeleteMenu).setOnMenuItemClickListener {
                viewModel.deleteLocalJokeById(args.jokeId)
                true
            }
        }
        viewModel.getJokeById(args.jokeId) { joke ->
            fragmentJokesItemEditTypeInput.setText(joke.type)
            fragmentJokesItemEditSetupInput.setText(joke.setup)
            fragmentJokesItemEditPunchlineInput.setText(joke.punchline)
        }
    }

    override fun initCollectors(): Unit = with(viewModel) {
        collectWithLifecycle(state) { state ->
            when (state) {
                CommonState.NORMAL -> {}
                CommonState.NAVIGATE_UP -> {
                    navController.navigateUp()
                }

                else -> throw IllegalStateException("Unknown state Other Profile: $state")
            }
        }
    }

}