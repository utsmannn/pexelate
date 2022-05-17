@file:Suppress("UNCHECKED_CAST")

package com.utsman.detail.navigator

import android.app.Activity
import com.utsman.detail.DetailActivity
import com.utsman.navigator.activity.DetailActivityContainer

class ActivityContainerImpl : DetailActivityContainer {
    override val activityDetailClass: Class<Activity>
        get() = DetailActivity::class.java as Class<Activity>
}