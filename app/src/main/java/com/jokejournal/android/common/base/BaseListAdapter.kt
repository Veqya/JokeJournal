package com.jokejournal.android.common.base

import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

typealias OnItemClick<T> = (item: T) -> Unit

abstract class BaseListAdapter<
        Binding : ViewBinding,
        Item,
        ViewHolder : BaseViewHolder<Binding, Item>>(diffCallback: AsyncDifferConfig.Builder<Item>) :
    ListAdapter<Item, ViewHolder>(diffCallback.build()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))
}