package com.jmoreno.list.ui

import com.jmoreno.list.ui.models.FetchRewardsGroupUI

data class FetchListViewState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val data: List<FetchRewardsGroupUI> = listOf()
)