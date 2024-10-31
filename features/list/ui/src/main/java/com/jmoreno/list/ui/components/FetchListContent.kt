package com.jmoreno.list.ui.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jmoreno.list.ui.FetchListViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FetchListContent(
    modifier: Modifier = Modifier,
    viewModel: FetchListViewModel = koinViewModel()
) {
    val state = viewModel.viewState.collectAsState()

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
        LazyColumn(modifier = modifier.fillMaxSize()) {
            state.value.data.forEach { group ->
                stickyHeader(key = group.groupId) {
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.tertiaryContainer)
                            .fillParentMaxWidth()
                    ) {
                        Text(
                            "Group ${group.groupId}",
                            modifier = Modifier.padding(
                                top = 16.dp,
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 8.dp
                            ),
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }

                }
                items(group.items, key = { it.id }) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 4.dp,
                                bottom = 4.dp,
                                start = 4.dp
                            ),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        ),
                        elevation = CardDefaults.elevatedCardElevation()
                    ) {
                        Box(
                            modifier = Modifier.padding(
                                top = 16.dp,
                                bottom = 4.dp,
                                start = 16.dp
                            )
                        ) {
                            Text(text = "Name = ${item.name}, Id = ${item.id}")
                        }
                    }
                }
            }


        }
    }

}