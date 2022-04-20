package com.utsman.homepage

import androidx.fragment.app.Fragment
import com.utsman.core.data.BaseEquatable

data class HomepageItem(
    var fragment: Class<Fragment>
) : BaseEquatable(fragment.name)