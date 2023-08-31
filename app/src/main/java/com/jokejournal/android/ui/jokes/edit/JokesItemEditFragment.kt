package com.jokejournal.android.ui.jokes.edit

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.navArgs
import com.jokejournal.android.R
import com.jokejournal.android.common.base.BaseFragment
import com.jokejournal.android.common.base.BindingInitializer
import com.jokejournal.android.databinding.FragmentJokesItemEditBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JokesItemEditFragment : BaseFragment<FragmentJokesItemEditBinding, JokesItemEditViewModel>() {
    override val bindingInitializer: BindingInitializer = FragmentJokesItemEditBinding::inflate
    override val viewModel: JokesItemEditViewModel by hiltNavGraphViewModels(R.id.jokes_nav)
    private val args by navArgs<JokesItemEditFragmentArgs>()
    override fun initViews(): Unit = with(viewBinding) {
        with(fragmentJokesItemEditToolbar.menu) {
            findItem(R.id.jokeEditMenu)
                .setOnMenuItemClickListener {
                    viewModel.editLocaleJoke(
                        id = args.jokeId,
                        type = fragmentJokesItemEditTypeInput.text.toString(),
                        setup = fragmentJokesItemEditSetupInput.text.toString(),
                        punchline = fragmentJokesItemEditPunchlineInput.text.toString()
                    )
                    true
                }
            findItem(R.id.jokeDeleteMenu).setOnMenuItemClickListener {
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

}