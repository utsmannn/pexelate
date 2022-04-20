package com.utsman.collection.navigator

import androidx.fragment.app.Fragment
import com.utsman.collection.presenter.CollectionHomeFragment
import com.utsman.navigator.asFragmentClass
import com.utsman.navigator.fragment.CollectionFragmentContainer

class FragmentContainerImpl : CollectionFragmentContainer {
    override val fragmentHomeClass: Class<Fragment>
        get() = CollectionHomeFragment::class.java.asFragmentClass()
}