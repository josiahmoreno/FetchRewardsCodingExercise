package com.jmoreno.list.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.compose.rememberConstraintsSizeResolver
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.jmoreno.list.ui.models.EventItemUI


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsDetailScreen(eventItemUI: EventItemUI) {
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val sizeResolver = rememberConstraintsSizeResolver()
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalPlatformContext.current)
            .data(eventItemUI.imgSrc)
            .size(sizeResolver)
            .build(),
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Box(modifier = Modifier) {
                // Background image using Coil
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(eventItemUI.imgSrc) // Replace with your image URL
                        .crossfade(true)
                        .build(),
                    contentDescription = "Background Image",
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop
                )
                LibraryTopBar(
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.primary,
//                ),
                    title = {
                        Text("fsdfsd",)
//                    AsyncImage(model = eventItemUI.imgSrc,  contentDescription = "",
//                        contentScale = ContentScale.Crop,
//                        modifier =
//                    )
                    },
                    scrollBehavior = scrollBehavior,
                    isCollapsed = false,
                    modifier = Modifier
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(
            start = 16.dp,
            top = 16.dp,
            end = 16.dp
        )) {
            Text(eventItemUI.dateFormatted.date)
            Text(eventItemUI.title)
            Text(eventItemUI.locationLine1)
            Text(eventItemUI.locationLine2)
            Text(eventItemUI.description)
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LibraryTopBar(
    title: @Composable () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    isCollapsed: Boolean,
    modifier: Modifier
) = LargeTopAppBar(
    modifier = modifier,
    title = title,
    expandedHeight = 300.dp,
    colors = TopAppBarDefaults.mediumTopAppBarColors(
        //containerColor = MaterialTheme.colorScheme.primary,
        containerColor = Color.Transparent,
        scrolledContainerColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.onBackground,
    ),
    scrollBehavior = scrollBehavior,

)
