package com.bkcoding.paging3compose.data.model


data class BeerDto(
    val boil_volume: BoilVolume,
    val brewers_tips: String,
    val contributed_by: String,
    val description: String,
    val first_brewed: String,
    val food_pairing: List<String>,
    val image_url: String,
    val ingredients: Ingredients,
    val method: Method,
    val name: String,
    val tagline: String,
    val volume: Volume
)