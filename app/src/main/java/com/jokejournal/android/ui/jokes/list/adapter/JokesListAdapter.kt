package com.jokejournal.android.ui.jokes.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import com.jokejournal.android.common.base.BaseListAdapter
import com.jokejournal.android.common.base.BaseViewHolder
import com.jokejournal.android.common.base.OnItemClick
import com.jokejournal.android.databinding.ItemJokeBinding
import entities.remote.Joke

class JokesListAdapter(private val onItemClick: OnItemClick<Joke>) :
    BaseListAdapter<ItemJokeBinding, Joke, JokesListAdapter.ViewHolder>(
        AsyncDifferConfig.Builder(DiffCallback())
    ) {
    override fun getItemViewType(position: Int) = position

    class DiffCallback : DiffUtil.ItemCallback<Joke>() {
        override fun areItemsTheSame(oldItem: Joke, newItem: Joke): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Joke, newItem: Joke): Boolean =
            oldItem == newItem
    }

    inner class ViewHolder(
        private val binding: ItemJokeBinding,
        private val onItemClick: OnItemClick<Joke>,
    ) : BaseViewHolder<ItemJokeBinding, Joke>(binding) {
        override fun bind(item: Joke) = with(binding) {
            root.setOnClickListener {
                onItemClick(item)
            }
            with(item) {
                itemJokePunchline.text = punchline
                itemJokeSetup.text = setup
                itemJokeType.text = type
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemJokeBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onItemClick
    )

}