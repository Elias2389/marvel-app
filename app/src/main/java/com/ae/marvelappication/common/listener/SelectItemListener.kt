package com.ae.marvelappication.common.listener

import com.ae.marvelappication.dto.ResultsItem

interface SelectItemListener {
    fun goToDetail(character: ResultsItem)
}