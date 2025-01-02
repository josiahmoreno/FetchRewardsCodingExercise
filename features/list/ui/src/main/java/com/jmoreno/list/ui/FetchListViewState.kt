package com.jmoreno.list.ui

import com.jmoreno.list.ui.models.EventItemUI

data class FetchListViewState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val data: List<EventItemUI> = listOf()
)
/*
sealed class FetchListViewState {
    class Error: FetchListViewState()
    class Loading: FetchListViewState()
    data class Successful(val data: List<EventItemUI> = listOf()):  FetchListViewState()
}
 */