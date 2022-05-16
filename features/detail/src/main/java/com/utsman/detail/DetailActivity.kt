package com.utsman.detail

import android.os.Bundle
import com.utsman.core.extensions.loadImage
import com.utsman.core.view.binding.BindingActivity
import com.utsman.detail.databinding.ActivityDetailBinding

class DetailActivity : BindingActivity<ActivityDetailBinding>() {

    override fun inflateBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateBinding(savedInstanceState: Bundle?) {
        with(binding) {
            val photoUrl = intent.getStringExtra("image_url").orEmpty()

            if (photoUrl.isNotEmpty()) {
                detailImagePhoto.loadImage(photoUrl)
            }
        }
    }
}