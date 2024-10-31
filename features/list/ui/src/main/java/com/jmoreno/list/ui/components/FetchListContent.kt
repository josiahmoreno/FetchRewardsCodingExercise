package com.jmoreno.list.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.jmoreno.list.ui.FetchListViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FetchListContent(
    modifier: Modifier = Modifier,
    viewModel: FetchListViewModel = koinViewModel()
) {
    val state = viewModel.viewState.collectAsState()



}