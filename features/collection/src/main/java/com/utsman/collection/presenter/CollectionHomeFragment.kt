package com.utsman.collection.presenter

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.utsman.collection.R
import com.utsman.collection.data.entity.Collection
import com.utsman.collection.databinding.FragmentCollectionHomeBinding
import com.utsman.collection.databinding.ItemCollectionHomeBinding
import com.utsman.core.extensions.*
import com.utsman.core.view.adapter.GenericAdapter
import com.utsman.core.view.binding.BindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CollectionHomeFragment : BindingFragment<FragmentCollectionHomeBinding>() {
    private val viewModel: CollectionHomeViewModel by viewModel()

    private val collectionAdapter: GenericAdapter<Collection> by genericAdapterLazy(
        layoutRes = R.layout.item_collection_home,
        onBindItem = { _, item ->
            ItemCollectionHomeBinding.bind(this).apply {
                imageCollectionFeature.loadImage(item.cover)
                tvCollectionFeature.text = item.title
            }
        }
    )

    override fun inflateBinding(): FragmentCollectionHomeBinding {
        return FragmentCollectionHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateBinding(savedInstanceState: Bundle?) {
        setupView()
        subscribe()
    }

    private fun setupView() = with(binding) {
        collectionRvFeatures.apply {
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            adapter = collectionAdapter
        }
    }

    private fun subscribe() = with(viewModel) {
        getCollectionHome()
        collectionHomeList.observe(viewLifecycleOwner) { state ->
            state.onLoading {
                collectionAdapter.pushLoading()
            }

            state.onSuccess {
                collectionAdapter.pushItems(it)
            }

            state.onFailure {
                collectionAdapter.pushError(it)
            }
        }
    }
}