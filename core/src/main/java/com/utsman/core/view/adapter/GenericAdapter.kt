package com.utsman.core.view.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utsman.core.R
import com.utsman.core.data.BaseEquatable
import com.utsman.core.data.Equatable
import com.utsman.core.extensions.inflateRoot
import kotlin.properties.Delegates

class GenericAdapter<T : Equatable>(
    @LayoutRes private val layoutRes: Int,
    @LayoutRes private val layoutPlaceholder: Int = R.layout.core_item_loading,
    @LayoutRes private val layoutEmpty: Int = R.layout.core_item_empty,
    @LayoutRes private val layoutError: Int = R.layout.core_item_error
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), AutoUpdatableAdapter {

    private var isChangesItems = false

    private var items: List<Equatable> by Delegates.observable(listOf()) { _, oldValue, newValue ->
        if (isChangesItems) {
            notifyDataSetChanged()
            isChangesItems = false
        } else {
            autoNotify(oldValue, newValue) { o, n ->
                o.longId == n.longId
            }
        }
    }

    private var attachedRecyclerView: RecyclerView? = null

    var onBindItem: View.(position: Int, item: T) -> Unit = { _, _ -> }
    var onErrorBindItem: View.(message: String, () -> Unit) -> Unit = { _, _ -> }
    var onEmptyBindItem: View.(message: String) -> Unit = {}
    var retryAction: () -> Unit = {}

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        attachedRecyclerView = recyclerView

        val attachedLayoutManager = recyclerView.layoutManager

        if (attachedLayoutManager is GridLayoutManager) {
            val spanCount = attachedLayoutManager.spanCount
            attachedLayoutManager.spanSizeLookup = setGridSpan(spanCount)
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        attachedRecyclerView = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Type.DATA -> DataViewHolder<T>(parent.inflateRoot(layoutRes))
            Type.LOADING -> LoadingViewHolder(parent.inflateRoot(layoutPlaceholder))
            Type.EMPTY -> EmptyViewHolder(parent.inflateRoot(layoutEmpty))
            else -> ErrorViewHolder(parent.inflateRoot(layoutError))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        when (getItemViewType(position)) {
            Type.DATA -> {
                holder.setIsRecyclable(false)
                try {
                    (holder as DataViewHolder<T>).bind(position, item as T, onBindItem)
                } catch (e: NullPointerException) {
                    e.printStackTrace()
                }
            }
            Type.ERROR -> {
                (holder as ErrorViewHolder).bind(
                    (item as Error).message,
                    onErrorBindItem,
                    retryAction
                )
            }
            Type.EMPTY -> {
                (holder as EmptyViewHolder).bind(
                    (item as Empty).message,
                    onEmptyBindItem
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return items[position].longId
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Loading -> Type.LOADING
            is Error -> Type.ERROR
            is Empty -> Type.EMPTY
            else -> Type.DATA
        }
    }

    private fun setGridSpan(column: Int): GridLayoutManager.SpanSizeLookup {
        return object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (getItemViewType(position)) {
                    Type.DATA -> 1
                    else -> column
                }
            }
        }
    }

    fun addItems(items: List<T>) {
        val newItem = calculateMutableItems()
        if (items.isNotEmpty()) newItem.addAll(items)
        if (newItem.isEmpty()) newItem.add(Empty(""))

        this.items = newItem
    }

    fun pushLoading() {
        val newItem = calculateMutableItems()
        newItem.add(Loading)
        items = newItem
    }

    fun pushError(throwable: Throwable) {
        val newItem = calculateMutableItems()
        newItem.add(Error(throwable.localizedMessage.orEmpty()))
        items = newItem
        attachedRecyclerView?.smoothScrollToPosition(itemCount)
    }

    fun pushEmpty(message: String) {
        val newItem = calculateMutableItems()
        newItem.add(Empty(message))
        items = newItem
        attachedRecyclerView?.smoothScrollToPosition(itemCount)
    }

    fun changeItem(items: List<T>) {
        isChangesItems = true
        this.items = items
    }

    private fun calculateMutableItems(): MutableList<Equatable> {
        val newItem = this.items.toMutableList()
        newItem.remove(Loading)
        newItem.remove(newItem.find { it is Error })
        newItem.remove(newItem.find { it is Empty})
        return newItem
    }

    class DataViewHolder<T : Equatable>(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int, item: T, onBind: View.(position: Int, item: T) -> Unit) {
            onBind.invoke(itemView, position, item)
        }
    }

    class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class ErrorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(
            message: String,
            onBind: View.(String, () -> Unit) -> Unit,
            retryAction: () -> Unit
        ) {
            onBind.invoke(itemView, message, retryAction)
            itemView.findViewById<Button>(R.id.item_btn_retry).setOnClickListener {
                retryAction.invoke()
            }
        }
    }

    class EmptyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(message: String, onBind: View.(message: String) -> Unit) {
            onBind.invoke(itemView, message)
        }
    }

    object Loading : BaseEquatable(ITEM_LOADING)
    data class Error(val message: String) : BaseEquatable(ITEM_ERROR)
    data class Empty(val message: String) : BaseEquatable(ITEM_EMPTY)

    object Type {
        const val DATA = 1
        const val LOADING = 2
        const val ERROR = 3
        const val EMPTY = 4
    }

    companion object {
        const val ITEM_LOADING = "loading"
        const val ITEM_ERROR = "error"
        const val ITEM_EMPTY = "empty"
    }
}