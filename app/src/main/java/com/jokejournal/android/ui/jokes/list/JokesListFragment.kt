package com.jokejournal.android.ui.jokes.list

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.jokejournal.android.R
import com.jokejournal.android.common.base.BaseFragment
import com.jokejournal.android.common.base.BindingInitializer
import com.jokejournal.android.databinding.FragmentJokesListBinding

class JokesListFragment : BaseFragment<FragmentJokesListBinding, JokesListViewModel>() {
    override val bindingInitializer: BindingInitializer = FragmentJokesListBinding::inflate
    override val viewModel: JokesListViewModel by hiltNavGraphViewModels(R.id.jokes_nav)

    override fun initViews(): Unit = with(viewBinding) {

    }

    override fun initCollectors(): Unit = with(viewModel) {

    }

}