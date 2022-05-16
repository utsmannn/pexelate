package com.utsman.detail.navigator

import android.app.Activity
import com.utsman.detail.DetailActivity
import com.utsman.navigator.activity.DetailActivityContainer
import com.utsman.navigator.asActivityClass

class ActivityContainerImpl : DetailActivityContainer {
    override val activityDetailClass: Class<Activity>
        get() = DetailActivity::class.java.asActivityClass()
}