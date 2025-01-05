package com.jmoreno.list.domain

data class EventsDomainModel(
    val id: Int,
    val imgSrc: String?,
    val phone: String?,
    val timeStamp: String,
    val title: String,
    val locationline1: String,
    val locationline2: String,
    val description: String
)
