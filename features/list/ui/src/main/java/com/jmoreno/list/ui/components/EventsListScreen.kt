package com.jmoreno.list.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jmoreno.list.ui.EventsListViewModel
import com.jmoreno.list.ui.R
import com.jmoreno.list.ui.models.EventItemUI
import org.koin.androidx.compose.koinViewModel
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import com.jmoreno.list.ui.FetchListViewState

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3AdaptiveApi::class
)
@Composable
fun EventsListScreen(
    modifier: Modifier = Modifier,
    viewModel: EventsListViewModel = koinViewModel(),
    appName: String,
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<EventItemUI>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    val state: State<FetchListViewState> = viewModel.viewState.collectAsState()
    val navigationHandler: (NavigationAction) -> Unit = { action ->
        when (action) {
            is NavigationAction.OnFixtureClick -> {
                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, action.eventItemUI)
            }
            // other navigation events
        }
    }
    ListDetailPaneScaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            //.padding(paddingValues = it)
        ,
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane(
                modifier = Modifier.fillMaxSize()

            ) {
                ListScreen(state = state, viewModel, appName = appName, onItemClick = {
                    navigationHandler(NavigationAction.OnFixtureClick(it))
                })
            }
        },
        detailPane = {
            AnimatedPane(
                modifier = Modifier.fillMaxSize()

            ){
                navigator.currentDestination?.content?.let {
                    //MatchDetailScreen(it)
                    //EnterAnimation {
                        EventsDetailScreen(it)
                  //  }
                }
            }
        },
    )

}

@Composable
fun EnterAnimation(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visibleState = MutableTransitionState(
            initialState = false
        ).apply { targetState = true },
        modifier = Modifier,
        enter = slideInHorizontally(),
        exit = slideOutHorizontally(),
    ) {
        content()
    }
}
sealed interface UserAction

sealed interface NavigationAction : UserAction {
    data class OnFixtureClick(val eventItemUI: EventItemUI) : NavigationAction
    // other navigation actions could go here.
}

sealed interface FixturesAction : UserAction {
    data class OnFixtureClick(val id: Int) : FixturesAction
    // other fixtures specific actions here
}

sealed interface MatchDetailAction : UserAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(state: State<FetchListViewState> , viewModel: EventsListViewModel, appName: String, onItemClick: (EventItemUI) -> Unit){
    val snackBarHostState = remember { SnackbarHostState() }
//    val scrollBehavior =
//        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
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
        modifier = Modifier.fillMaxSize()
        //.nestedScroll(scrollBehavior.nestedScrollConnection)
        ,
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
                },
                //scrollBehavior = scrollBehavior,
                //isCollapsed = false
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
                    .fillMaxSize()

            ) {
                //state.value.data.forEach { group: EventItemUI ->
//                    stickyHeader(key = group.groupId) {
//                        GroupHeader(group = group)
//                    }
                items(state.value.data, key = {
                    //println(it.id)
                    it.id
                }) { item ->
                    ItemCard(item, onItemClick = onItemClick)
                }
                //}
            }
        }
    }
}


