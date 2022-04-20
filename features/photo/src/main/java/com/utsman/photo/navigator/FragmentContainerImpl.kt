package com.utsman.photo.navigator

import androidx.fragment.app.Fragment
import com.utsman.navigator.asFragmentClass
import com.utsman.navigator.fragment.PhotoFragmentContainer
import com.utsman.photo.presenter.PhotoHomeFragment

class FragmentContainerImpl : PhotoFragmentContainer {
    override val fragmentHomeClass: Class<Fragment>
        get() = PhotoHomeFragment::class.java.asFragmentClass()
}