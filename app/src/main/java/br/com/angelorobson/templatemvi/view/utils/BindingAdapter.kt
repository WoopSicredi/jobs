package br.com.angelorobson.templatemvi.view.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer


abstract class BindingAdapter<A, B : ViewDataBinding> :
        ListAdapter<A, BindingAdapter<A, B>.BindingViewHolder<B>>(DiffUtilCallback<A>()) {

    @LayoutRes
    abstract fun getLayoutResId(): Int

    abstract fun onBindViewHolder(binding: B, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<B> {
        val view = LayoutInflater.from(parent.context).inflate(getLayoutResId(), parent, false)
        return BindingViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutResId()
    }

    override fun onBindViewHolder(holder: BindingViewHolder<B>, position: Int) {
        holder.binding?.let { onBindViewHolder(it, position) }
    }

    inner class BindingViewHolder<B : ViewDataBinding>(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        val binding = DataBindingUtil.bind<B>(containerView)
    }

}