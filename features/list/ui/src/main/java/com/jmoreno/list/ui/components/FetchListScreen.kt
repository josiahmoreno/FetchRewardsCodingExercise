package com.jmoreno.list.ui.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jmoreno.list.ui.FetchListViewModel
import com.jmoreno.list.ui.models.FetchRewardsGroupUI
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FetchListScreen(
    modifier: Modifier = Modifier,
    viewModel: FetchListViewModel = koinViewModel()
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val state = viewModel.viewState.collectAsState()
    LaunchedEffect(state.value.isError) {
        if(state.value.isError) {
            val result = snackBarHostState.showSnackbar(
                message = "Error fetching from api.",
                actionLabel = "Retry",
                duration = SnackbarDuration.Indefinite
            )
            when (result) {
                SnackbarResult.Dismissed -> {}
                SnackbarResult.ActionPerformed -> {
                    viewModel.refresh()
                }
            }
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ){ innerPadding ->
        val pullRefreshState = rememberPullToRefreshState()
        PullToRefreshBox(
            state = pullRefreshState,
            isRefreshing = state.value.isLoading,
            onRefresh = {
                viewModel.refresh()
            },
            indicator = {
                PullToRefreshDefaults.Indicator(
                    state = pullRefreshState,
                    isRefreshing = state.value.isLoading,
                    modifier = Modifier.align(Alignment.TopCenter),
                )
            }
        ) {
            LazyColumn(modifier = modifier.padding(innerPadding).fillMaxSize()) {
                state.value.data.forEach { group: FetchRewardsGroupUI ->
                    stickyHeader(key = group.groupId) {
                        GroupHeader(group = group)
                    }
                    items(group.items, key = { it.id }) { item ->
                        ItemCard(item)
                    }
                }
            }
        }
    }

}