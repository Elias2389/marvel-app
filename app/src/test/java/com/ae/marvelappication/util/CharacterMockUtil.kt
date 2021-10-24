package com.ae.marvelappication.util

import com.ae.marvelappication.data.entity.ResultsItemEntity
import com.ae.marvelappication.data.entity.ThumbnailEntity
import com.ae.marvelappication.dto.Comics
import com.ae.marvelappication.dto.Events
import com.ae.marvelappication.dto.ResultsItem
import com.ae.marvelappication.dto.Series
import com.ae.marvelappication.dto.Stories
import com.ae.marvelappication.dto.Thumbnail

val characterMock: ResultsItem = ResultsItem(
    id = 12334,
    thumbnail = Thumbnail(),
    comics = Comics(),
    series = Series(),
    events = Events(),
    stories = Stories(),
    urls = emptyList()
)


val characterEntityMock: ResultsItemEntity = ResultsItemEntity(
    id = 12334,
    thumbnail = ThumbnailEntity(),
    name = "Test mock",
    description = "Description mock"
)