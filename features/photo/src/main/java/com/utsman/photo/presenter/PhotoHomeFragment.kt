package com.utsman.photo.presenter

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utsman.core.extensions.*
import com.utsman.core.view.adapter.GenericAdapter
import com.utsman.core.view.binding.BindingFragment
import com.utsman.navigator.ActivityNavigator
import com.utsman.photo.R
import com.utsman.photo.data.entity.Photo
import com.utsman.photo.databinding.FragmentPhotoHomeBinding
import com.utsman.photo.databinding.ItemPhotoHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoHomeFragment : BindingFragment<FragmentPhotoHomeBinding>() {

    private val viewModel: PhotoHomeViewModel by viewModel()

    private val photoAdapter: GenericAdapter<Photo> by genericAdapterLazy(
        layoutRes = R.layout.item_photo_home,
        onBindItem = { _, item ->
            ItemPhotoHomeBinding.bind(this).apply {
                imagePhotoCurated.loadImage(item.sources.medium)
                root.setOnClickListener {

                    val start = System.currentTimeMillis()

                    val detailClass = ActivityNavigator.DETAIL_ACTIVITY_CONTAINER.activityDetailClass
                    val intent = Intent(context, detailClass).apply {
                        putExtra("image_url", item.sources.medium)
                    }

                    println("cost time ---> ${System.currentTimeMillis()-start}")
                    startActivity(intent)
                }
            }
        }
    )

    override fun inflateBinding(): FragmentPhotoHomeBinding {
        return FragmentPhotoHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateBinding(savedInstanceState: Bundle?) {
        setupView()
        subscribe()
    }

    private fun setupView() = with(binding) {
        photoRvCurated.apply {
            val linearLayout = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            layoutManager = linearLayout
            adapter = photoAdapter
        }
    }

    private fun subscribe() = with(viewModel) {
        getListPhoto()
        photoListEventLiveData.observe(viewLifecycleOwner) { state ->
            state.onLoading {
                photoAdapter.pushLoading()
            }
            state.onSuccess {
                photoAdapter.addItems(it)
            }
            state.onFailure {
                photoAdapter.pushError(it)
            }
        }
    }
}