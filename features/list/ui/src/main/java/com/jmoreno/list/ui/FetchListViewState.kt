package com.jmoreno.list.ui

import com.jmoreno.list.ui.models.EventItemUI

data class FetchListViewState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isNavigatingBack: Boolean = false,
    val data: List<EventItemUI> = listOf(),
    val detail: EventItemUI? = null
)