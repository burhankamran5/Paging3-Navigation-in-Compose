package com.bkcoding.paging3compose.mappers

import com.bkcoding.paging3compose.data.model.BeerData
import com.bkcoding.paging3compose.data.model.BeerDto

fun BeerDto.toBeerData() = BeerData(
    name = name,
    description = description,
    image_url = image_url
)
