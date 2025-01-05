package com.jmoreno.list.ui.models

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class EventItemUI(
    val id: Int,
    val imgSrc: String?,
    val phone: String?,
    val dateOfEventFormatted: DateFormatted,
    val title: String,
    val locationLine1: String,
    val locationLine2: String,
    val description: String
) : Parcelable