package com.utsman.core.extensions

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.replaceToFragment(
    container: FrameLayout,
    fragment: Class<out Fragment>,
    arg: Bundle? = null,
    isUnique: Boolean = false
) {
    if (isUnique) {
        val newId = View.generateViewId()
        container.id = newId
    }

    val containerId = container.id
    val tag = fragment.name

    beginTransaction()
        .replace(containerId, fragment, arg)
        .setReorderingAllowed(true)
        .addToBackStack(tag)
        .commit()
}