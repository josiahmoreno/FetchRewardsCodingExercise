package com.jmoreno.list.ui.components

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp

import com.jmoreno.list.ui.FetchListViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FetchListScreen(modifier: Modifier = Modifier, viewModel: FetchListViewModel = koinViewModel()) {
    val state = viewModel.viewState.collectAsState()
    val pullRefreshState = rememberPullToRefreshState()
    var text = remember { "Pull" }
//    val loading = remember { state.value.isLoading}
//    if (pullRefreshState.isRefreshing) {
//        LaunchedEffect(true) {
//            // fetch something
//            viewModel.refresh()
//        }
//    }
//    LaunchedEffect(state.value.isLoading) {
//        if(state.value.isLoading) {
//           pullRefreshState.startRefresh()
//            //0pullRefreshState.endRefresh()
//        } else {
//            pullRefreshState.endRefresh()
//        }
//    }
//    LaunchedEffect(key1 = pullRefreshState.isRefreshing) {
//        if (pullRefreshState.isRefreshing) {
//            viewModel.refresh()
//            pullRefreshState.endRefresh()
//        }
//
//    }



//    if(!state.value.isLoading) {
//        pullRefreshState.endRefresh()
//    }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(pullRefreshState.nestedScrollConnection)
        ) {
            text = if (pullRefreshState.isRefreshing) "isRefreshing" else "isNOTRefreshing"
            LaunchedEffect(pullRefreshState.isRefreshing){
                if(pullRefreshState.isRefreshing){
                    viewModel.refresh()
                }
            }
            LaunchedEffect(state.value.isLoading) {
                if(state.value.isLoading){
                    pullRefreshState.startRefresh()
                } else {
                    pullRefreshState.endRefresh()
                }
            }

//            if(state.value.isError){
//                Column(
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier.fillMaxSize().nestedScroll(pullRefreshState.nestedScrollConnection)
//                ) {
//                    Text(
//                        "Error",
//                        modifier = Modifier.fillMaxSize().wrapContentHeight(),
//                        style = MaterialTheme.typography.headlineMedium,
//                        color = MaterialTheme.colorScheme.onSurface,
//                        textAlign = TextAlign.Center
//                    )
//                }
//            } else
                if (state.value.isLoading) {

//                Box(modifier = Modifier.fillMaxSize().background(color = Color.Green), contentAlignment = Alignment.Center) {
//                    CircularProgressIndicator(
//                        modifier = Modifier.width(width = 64.dp),
//                        color = MaterialTheme.colorScheme.secondary,
//                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
//                    )
//                }
            } else {
                LazyColumn(modifier = modifier.fillMaxSize()) {
                    state.value.data.forEach { item1 ->

                        stickyHeader(key = item1.groupId) {
                            Box(
                                modifier = Modifier.background(MaterialTheme.colorScheme.tertiaryContainer)
                                    .fillParentMaxWidth()
                            ) {
                                Text(
                                    "Group ${item1.groupId}",
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
                        items(item1.items, key = { it.id }) { itemsmall ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp, bottom = 4.dp, start = 4.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                ), elevation = CardDefaults.elevatedCardElevation()
                            ) {
                                Box(
                                    modifier = Modifier.padding(
                                        top = 16.dp,
                                        bottom = 4.dp,
                                        start = 16.dp
                                    )
                                ) {
                                    // Text(text = "ID = ${itemsmall.id}")
                                    Text(text = "Name = ${itemsmall.name}, Id = ${itemsmall.id}")
                                }
                            }
                            //GroupCard(data = data)
                        }
                    }

//            items(state.value.data){ item1 ->
//                item(key = item1.groupId){
//                    0
//                }
//            }
//            items(state.value.data, key = { aa: FetchRewardsItemUI ->
//                aa.groupTitle.toInt()
//            }) { data: FetchRewardsItemUI ->
//                GroupCard(data = data)
//            }
                }
            }
            Text("${text}", Modifier.padding(40.dp))
            PullToRefreshContainer(
                modifier = Modifier.align(alignment = Alignment.TopCenter),
                state = pullRefreshState,
            )
        }

}



/*
fun ListScreen(modifier: Modifier = Modifier, viewModel: ListViewModel = koinViewModel()) {
    val state = viewModel.viewState.collectAsState()
    val pullRefreshState = rememberPullToRefreshState()
    if (pullRefreshState.isRefreshing) {
        LaunchedEffect(true) {
            // fetch something
            viewModel.refresh()
        }
    }

    if(state.value.isError){
        if(pullRefreshState.isRefreshing){
            pullRefreshState.endRefresh()
        }
        Text("Error",
            modifier = Modifier.fillMaxSize().wrapContentHeight(),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
    }
    else if (state.value.isLoading) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier.width(width = 64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    } else {
        if(pullRefreshState.isRefreshing){
            pullRefreshState.endRefresh()
        }
        Box(
            modifier = modifier
                .nestedScroll(pullRefreshState.nestedScrollConnection)
        ) {
            LazyColumn(modifier = modifier) {
                state.value.data.forEach { item1 ->

                    stickyHeader(key = item1.groupId) {
                        Box(
                            modifier = Modifier.background(MaterialTheme.colorScheme.tertiaryContainer)
                                .fillParentMaxWidth()
                        ) {
                            Text(
                                "Group ${item1.groupId}",
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
                    items(item1.items, key = { it.id }) { itemsmall ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp, bottom = 4.dp, start = 4.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            ), elevation = CardDefaults.elevatedCardElevation()
                        ) {
                            Box(
                                modifier = Modifier.padding(
                                    top = 16.dp,
                                    bottom = 4.dp,
                                    start = 16.dp
                                )
                            ) {
                                // Text(text = "ID = ${itemsmall.id}")
                                Text(text = "Name = ${itemsmall.name}, Id = ${itemsmall.id}")
                            }
                        }
                        //GroupCard(data = data)
                    }
                }

//            items(state.value.data){ item1 ->
//                item(key = item1.groupId){
//                    0
//                }
//            }
//            items(state.value.data, key = { aa: FetchRewardsItemUI ->
//                aa.groupTitle.toInt()
//            }) { data: FetchRewardsItemUI ->
//                GroupCard(data = data)
//            }
            }
            PullToRefreshContainer(
                modifier = Modifier.align(alignment = Alignment.TopCenter),
                state = pullRefreshState,
            )
        }

    }

}
 */