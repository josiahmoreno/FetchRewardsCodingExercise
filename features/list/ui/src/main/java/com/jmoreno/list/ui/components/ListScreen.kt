package com.jmoreno.list.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.jmoreno.list.ui.EventsListViewModel
import com.jmoreno.list.ui.FetchListViewState
import com.jmoreno.list.ui.models.EventItemUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    state: State<FetchListViewState>,
    scrollState: LazyListState,
    viewModel: EventsListViewModel,
    appName: String,
    placeHolder: Painter,
    onItemClick: (EventItemUI) -> Unit
) {

    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(state.value.isError) {
        if (state.value.isError) {
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
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(appName)
                }
            )
        }
    ) { innerPadding ->
        val pullRefreshState = rememberPullToRefreshState()
        PullToRefreshBox(
            modifier = Modifier.padding(innerPadding),
            state = pullRefreshState,
            isRefreshing = state.value.isLoading,
            onRefresh = {
                viewModel.refresh()
            }
        ) {
            LazyColumn(
                state = scrollState,
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(
                    top = 24.dp,
                    bottom = 24.dp,
                )
            ) {
                items(state.value.data, key = {
                    it.id
                }) { item ->
                    ItemCard(
                        item = item,
                        onItemClick = onItemClick,
                        placeHolder = placeHolder
                    )
                }
            }
        }
    }
}