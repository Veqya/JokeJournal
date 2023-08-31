package com.jokejournal.android.ui.jokes.edit

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.jokejournal.android.R
import com.jokejournal.android.common.base.BaseFragment
import com.jokejournal.android.common.base.BindingInitializer
import com.jokejournal.android.databinding.FragmentJokesItemEditBinding


class JokesItemEditFragment : BaseFragment<FragmentJokesItemEditBinding, JokesItemEditViewModel>() {
    override val bindingInitializer: BindingInitializer = FragmentJokesItemEditBinding::inflate
    override val viewModel: JokesItemEditViewModel by hiltNavGraphViewModels(R.id.jokes_nav)

}