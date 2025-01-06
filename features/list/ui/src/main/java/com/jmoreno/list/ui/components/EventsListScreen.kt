package com.jmoreno.list.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.PaneScaffoldDirective
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.jmoreno.list.ui.EventsListViewModel
import com.jmoreno.list.ui.FetchListViewState
import com.jmoreno.list.ui.models.EventItemUI
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun EventsListScreen(
    placeHolder: Painter,
    viewModel: EventsListViewModel = koinViewModel(),
    appName: String,
) {
    //  Part of getting rid of space in between detail and list
    //

    val systemDirective = calculatePaneScaffoldDirective(currentWindowAdaptiveInfo())
    val customDirective = PaneScaffoldDirective(
        maxHorizontalPartitions = systemDirective.maxHorizontalPartitions,
        horizontalPartitionSpacerSize = 0.dp,
        maxVerticalPartitions = systemDirective.maxVerticalPartitions,
        verticalPartitionSpacerSize = systemDirective.verticalPartitionSpacerSize,
        excludedBounds = systemDirective.excludedBounds,
        defaultPanePreferredWidth = systemDirective.defaultPanePreferredWidth
    )
    val navigator = rememberListDetailPaneScaffoldNavigator<EventItemUI>(customDirective)
    val coroutineScope = rememberCoroutineScope()
    BackHandler(navigator.canNavigateBack()) {
        coroutineScope.launch {
            navigator.navigateBack()
        }
    }
    val state: State<FetchListViewState> = viewModel.viewState.collectAsState()
    val scrollState: LazyListState = rememberSaveable(saver = LazyListState.Saver) {
        LazyListState()
    }
    ListDetailPaneScaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane(
                modifier = Modifier.fillMaxSize()
            ) {
                //  this is for having a No Event Selected. in the detail pane. For some reason
                //  No Event Selected. will show while the back navigation is animating in compact
                //  which looks wrong
                //

                if(state.value.isNavigatingBack){
                    viewModel.onNavigationFinished()
                }
                ListScreen(
                    state = state,
                    viewModel = viewModel,
                    scrollState = scrollState,
                    appName = appName,
                    placeHolder = placeHolder,
                    onItemClick = { eventItemUI ->
                        coroutineScope.launch {
                            viewModel.onEventCLicked(eventItemUI)
                            navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, eventItemUI)
                        }
                    })
            }
        },
        detailPane = {
            AnimatedPane(
                modifier = Modifier.fillMaxSize()
            ) {

                val detail = state.value.detail
                if (detail != null) {
                    EventsDetailScreen(
                        eventItemUI = detail,
                        onBackArrowPressed = {
                            coroutineScope.launch {
                                navigator.navigateBack()
                                viewModel.onEventCleared()
                            }
                        }, placeHolder = placeHolder
                    )
                } else {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    )
                    {
                        Text(
                            "No Event Selected.",
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        },
    )
}


