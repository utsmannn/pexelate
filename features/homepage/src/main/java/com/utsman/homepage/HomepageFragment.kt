package com.utsman.homepage

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.utsman.core.extensions.genericAdapterLazy
import com.utsman.core.extensions.replaceToFragment
import com.utsman.core.view.adapter.GenericAdapter
import com.utsman.core.view.binding.BindingFragment
import com.utsman.homepage.databinding.FragmentHomepageBinding
import com.utsman.homepage.databinding.ItemHomepageBinding
import com.utsman.navigator.FragmentNavigator

class HomepageFragment : BindingFragment<FragmentHomepageBinding>() {

    private val homepageAdapter: GenericAdapter<HomepageItem> by genericAdapterLazy(
        layoutRes = R.layout.item_homepage,
        onBindItem = { _, item -> bindViewHolder(this, item) }
    )

    private fun bindViewHolder(view: View, item: HomepageItem) {
        ItemHomepageBinding.bind(view).apply {
            childFragmentManager.replaceToFragment(
                container = root,
                fragment = item.fragment,
                isUnique = true
            )
        }
    }

    override fun inflateBinding(): FragmentHomepageBinding {
        return FragmentHomepageBinding.inflate(layoutInflater)
    }

    override fun onCreateBinding(savedInstanceState: Bundle?) {
        setupView()
        setupContent()
    }

    private fun setupView() = with(binding) {
        homepageRv.apply {
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            adapter = homepageAdapter
        }
    }

    private fun setupContent() {
        val fragmentPhoto = FragmentNavigator.PHOTO_FRAGMENT_CONTAINER.fragmentHomeClass
        val fragmentCollection = FragmentNavigator.COLLECTION_FRAGMENT_CONTAINER.fragmentHomeClass

        val content = listOf(fragmentPhoto, fragmentCollection).map {
            HomepageItem(it)
        }

        homepageAdapter.addItems(content)
    }
}