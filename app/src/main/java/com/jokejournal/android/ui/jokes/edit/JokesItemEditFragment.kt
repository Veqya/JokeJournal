package com.jokejournal.android.ui.jokes.edit

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jokejournal.android.R
import com.jokejournal.android.common.base.BaseFragment
import com.jokejournal.android.common.base.BindingInitializer
import com.jokejournal.android.databinding.FragmentJokesItemEditBinding
import com.jokejournal.android.utils.setTextIfDifferent
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
        viewModel.initLocalJokeById(args.jokeId)

        fragmentJokesItemEditTypeInput.addTextChangedListener { text ->
            viewModel.updateJokeTypeState(text.toString())
        }
        fragmentJokesItemEditSetupInput.addTextChangedListener { text ->
            viewModel.updateSetupTypeState(text.toString())
        }
        fragmentJokesItemEditPunchlineInput.addTextChangedListener { text ->
            viewModel.updatePunchlineState(text.toString())
        }
    }

    override fun initCollectors(): Unit = with(viewBinding) {
        collectWithLifecycle(viewModel.state) { state ->
            when (state) {
                CommonState.NORMAL -> {}
                CommonState.NAVIGATE_UP -> {
                    navController.navigateUp()
                }

                else -> throw IllegalStateException("Unknown state Other Profile: $state")
            }
        }
        collectWithLifecycle(viewModel.jokeUiState) { jokeUiState ->
            jokeUiState?.let { state ->
                fragmentJokesItemEditTypeInput.setTextIfDifferent(state.type)
                fragmentJokesItemEditSetupInput.setTextIfDifferent(jokeUiState.setup)
                fragmentJokesItemEditPunchlineInput.setTextIfDifferent(jokeUiState.punchline)
            }
        }
    }
}