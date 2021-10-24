package com.ae.marvelappication.ui.characterlist.adapter

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import com.ae.marvelappication.R
import com.ae.marvelappication.common.listener.SelectItemListener
import com.ae.marvelappication.databinding.MarvelAppItemCharacterBinding
import com.ae.marvelappication.dto.ResultsItem
import com.bumptech.glide.Glide
import com.xwray.groupie.viewbinding.BindableItem


class CharacterItem(
    private val context: Context,
    private val character: ResultsItem,
    private val listener: SelectItemListener
) : BindableItem<MarvelAppItemCharacterBinding>() {

    override fun initializeViewBinding(view: View): MarvelAppItemCharacterBinding {
        return MarvelAppItemCharacterBinding.bind(view)
    }

    override fun bind(viewBinding: MarvelAppItemCharacterBinding, position: Int) {
        viewBinding.title.text = character.name
        viewBinding.itemContainer.setOnClickListener { listener.goToDetail(character) }
        viewBinding.itemContainer.animation =
            AnimationUtils.loadAnimation(context, R.anim.marvel_app_recycler_anim)

        setImage(viewBinding)
    }

    private fun setImage(binding: MarvelAppItemCharacterBinding) {
        Glide.with(context)
            .load(character.thumbnail.path + IMAGE_VARIANT)
            .centerCrop()
            .into(binding.characterImage)
    }

    override fun getLayout(): Int = R.layout.marvel_app_item_character

    private companion object {
        const val IMAGE_VARIANT: String = "/portrait_small.jpg"
    }
}