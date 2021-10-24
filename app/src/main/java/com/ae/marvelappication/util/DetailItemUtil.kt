package com.ae.marvelappication.util

import com.ae.marvelappication.R
import com.ae.marvelappication.common.resource.ResourceProvider
import com.ae.marvelappication.dto.ResultsItem
import com.ae.marvelappication.ui.characterdetail.adapter.DetailItem

fun getDetailItems(resourceProvider: ResourceProvider, result: ResultsItem): List<DetailItem> =
    arrayListOf<DetailItem>().apply {
        add(
            DetailItem(
                resourceProvider.getString(R.string.marvel_app_comics),
                result.comics.available
            )
        )
        add(
            DetailItem(
                resourceProvider.getString(R.string.marvel_app_series),
                result.series.available
            )
        )
        add(
            DetailItem(
                resourceProvider.getString(R.string.marvel_app_stories),
                result.stories.available
            )
        )
        add(
            DetailItem(
                resourceProvider.getString(R.string.marvel_app_event),
                result.events.available
            )
        )
    }
