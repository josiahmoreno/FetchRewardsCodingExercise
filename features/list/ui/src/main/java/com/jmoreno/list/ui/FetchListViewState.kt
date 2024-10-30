package com.jmoreno.list.ui

data class FetchListViewState (
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val data: List<FetchRewardsItemUI> = listOf()
)