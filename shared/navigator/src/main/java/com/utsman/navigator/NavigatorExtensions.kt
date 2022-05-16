@file:Suppress("UNCHECKED_CAST")

package com.utsman.navigator

import android.app.Activity
import androidx.fragment.app.Fragment

fun <T: Fragment>Class<T>.asFragmentClass() : Class<Fragment> {
    return this as Class<Fragment>
}

fun <T: Activity>Class<T>.asActivityClass() : Class<Activity> {
    return this as Class<Activity>
}