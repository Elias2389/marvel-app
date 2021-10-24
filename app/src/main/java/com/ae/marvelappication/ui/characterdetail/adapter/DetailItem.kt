package com.ae.marvelappication.ui.characterdetail.adapter

import android.view.View
import com.ae.marvelappication.R
import com.ae.marvelappication.databinding.MarvelAppItemDetailBinding
import com.xwray.groupie.viewbinding.BindableItem


class DetailItem(
    private val title: String,
    private val count: Int
) : BindableItem<MarvelAppItemDetailBinding>() {

    override fun initializeViewBinding(view: View): MarvelAppItemDetailBinding {
        return MarvelAppItemDetailBinding.bind(view)
    }

    override fun bind(viewBinding: MarvelAppItemDetailBinding, position: Int) {
        viewBinding.itemTitle.text = title
        viewBinding.itemCount.text = count.toString()
    }

    override fun getLayout(): Int = R.layout.marvel_app_item_detail
}