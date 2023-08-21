package com.bkcoding.paging3compose.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class BeerData(
    val name: String,
    val description: String,
    val image_url: String,
) : Parcelable
