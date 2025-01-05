package com.jmoreno.list.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jmoreno.list.ui.EventsListViewModel
import com.jmoreno.list.ui.FetchListViewState
import com.jmoreno.list.ui.models.EventItemUI
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun EventsListScreen(
    modifier: Modifier = Modifier,
    viewModel: EventsListViewModel = koinViewModel(),
    appName: String,
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<EventItemUI>()
    val coroutineScope = rememberCoroutineScope()
    BackHandler(navigator.canNavigateBack()) {
        coroutineScope.launch {
            navigator.navigateBack()
        }

    }
   // var detailed by remember{ mutableStateOf<EventItemUI?>(null) }
    val state: State<FetchListViewState> = viewModel.viewState.collectAsState()
    val navigationHandler: (NavigationAction) -> Unit = { action ->
        when (action) {
            is NavigationAction.OnFixtureClick -> {
                coroutineScope.launch {
                    viewModel.onEventCLicked(action.eventItemUI)
                    navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, action.eventItemUI)
                }
            }
            // other navigation events
        }
    }
    ListDetailPaneScaffold(
        modifier = Modifier
            .fillMaxSize(),
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane(
                modifier = Modifier.fillMaxSize(),
//                enterTransition = slideInHorizontally (initialOffsetX = {-it}),
//                exitTransition = slideOutHorizontally (targetOffsetX = {-it})
            ) {
                println("josiah listPane ListScreen ${navigator.currentDestination!!.contentKey?.title}}")
                ListScreen(state = state, viewModel, appName = appName, onItemClick = {
                    navigationHandler(NavigationAction.OnFixtureClick(it))
                })
            }
        },
        detailPane = {
            AnimatedPane(
                modifier = Modifier.fillMaxSize(),
//                        enterTransition = slideInHorizontally (initialOffsetX = {-it}),
                exitTransition = slideOutHorizontally ()
            ) {
                state.value.detail?.let {
                    EventsDetailScreen(it,
                        onBackArrowPressed = {
                            coroutineScope.launch {
                                navigator.navigateBack()
                            }
                        })
                }
            }
        },
    )
}

sealed interface UserAction

sealed interface NavigationAction : UserAction {
    data class OnFixtureClick(val eventItemUI: EventItemUI) : NavigationAction
    // other navigation actions could go here.
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    state: State<FetchListViewState>,
    viewModel: EventsListViewModel,
    appName: String,
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
            state = pullRefreshState,
            isRefreshing = state.value.isLoading,
            onRefresh = {
                viewModel.refresh()
            }
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier
                    .padding(innerPadding)

                    .background(Color.White)
                    .fillMaxSize(),
                contentPadding = PaddingValues(
                    top = 24.dp,
                    bottom = 24.dp,
                )

            ) {
                items(state.value.data, key = {
                    it.id
                }) { item ->
                    ItemCard(item, onItemClick = onItemClick)
                }
            }
        }
    }
}


