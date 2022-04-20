package com.utsman.core.extensions

import android.view.View
import com.utsman.core.R
import com.utsman.core.data.Equatable
import com.utsman.core.view.adapter.GenericAdapter

fun <T : Equatable> genericAdapterLazy(
    layoutRes: Int,
    layoutPlaceholder: Int = R.layout.core_item_loading,
    layoutEmpty: Int =  R.layout.core_item_empty,
    onBindEmpty: View.(message: String) -> Unit = { },
    onBindItem: View.(position: Int, item: T) -> Unit = { _, _ -> }
): Lazy<GenericAdapter<T>> {
    return lazy {
        val adapter = GenericAdapter<T>(
            layoutRes = layoutRes,
            layoutPlaceholder = layoutPlaceholder,
            layoutEmpty = layoutEmpty
        )
        adapter.onEmptyBindItem = onBindEmpty
        adapter.onBindItem = onBindItem
        adapter
    }
}