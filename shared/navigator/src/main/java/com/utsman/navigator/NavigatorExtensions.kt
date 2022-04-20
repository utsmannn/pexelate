@file:Suppress("UNCHECKED_CAST")

package com.utsman.navigator

import androidx.fragment.app.Fragment

fun <T: Fragment>Class<T>.asFragmentClass() : Class<Fragment> {
    return this as Class<Fragment>
}