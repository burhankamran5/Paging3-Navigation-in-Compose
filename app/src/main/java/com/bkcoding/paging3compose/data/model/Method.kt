package com.bkcoding.paging3compose.data.model

data class Method(
    val fermentation: Fermentation,
    val mash_temp: List<MashTemp>,
    val twist: String
)