package com.utsman.pexelate

import android.os.Bundle
import com.utsman.collection.presenter.CollectionHomeFragment
import com.utsman.core.extensions.replaceToFragment
import com.utsman.core.view.binding.BindingActivity
import com.utsman.homepage.HomepageFragment
import com.utsman.pexelate.databinding.ActivityMainBinding
import com.utsman.photo.presenter.PhotoHomeFragment

class MainActivity : BindingActivity<ActivityMainBinding>() {

    override fun inflateBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreateBinding(savedInstanceState: Bundle?) {
        supportFragmentManager.replaceToFragment(
            binding.appFrame,
            HomepageFragment::class.java
        )
    }
}