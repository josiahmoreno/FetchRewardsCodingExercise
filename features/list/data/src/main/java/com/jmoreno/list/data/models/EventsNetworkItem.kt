package com.jmoreno.list.data.models

data class EventsNetworkItem(
    val id: Int,
    val description: String,
    val title: String,
    val timestamp: String,
    val image: String?,
    val date: String,
    val phone: String?,
    val locationline1: String,
    val locationline2: String
)