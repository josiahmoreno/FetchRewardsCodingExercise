package com.jmoreno.list.ui.models

data class EventItemUI(
    val id: Int,
    val imgSrc: String?,
    val dateFormatted: DateFormatted,
    val title: String,
    val locationLine1: String,
    val locationLine2: String,
    val description: String
)

@JvmInline
value class DateFormatted(val date: String)