package com.jmoreno.list.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.size.Scale
import coil3.size.Size
import com.jmoreno.list.ui.models.EventItemUI

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ItemCard(item: EventItemUI) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(306.dp)
            .padding(
                top = 4.dp,
                bottom = 4.dp,
                start = 16.dp,
                end = 16.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Box(
            Modifier.background(Color.Red)
        ) {
            AsyncImage(
                //model = item.imgSrc,
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(item.imgSrc)
                    //.size
                    .scale(Scale.FILL)
                    .build(),
                //error = painterResource(R.drawable.ic_broken_image),
                //placeholder = painterResource(R.drawable.loading_img),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier.padding(
                    top = 48.dp,
                    bottom = 48.dp,
                    start = 32.dp
                )
            ) {
                val headline = item.title
                val truncatedHeadline = if (item.title.length > 22) {
                    headline.take(22) + "…" // Add an ellipsis if the text exceeds 22 characters
                } else {
                    headline
                }
                Text(text = item.dateFormatted.date)
                Text(
                    text = truncatedHeadline,
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = item.locationLine1 +"\n"+item.locationLine2,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 16.dp)
                )
//                Text(
//                    text = item.locationLine2,
//                    style = MaterialTheme.typography.bodyMedium,
//                    modifier = Modifier.padding(top = 2.dp)
//                )
                val description = item.description
                val truncatedDescription = if (description.length > 22) {
                    description.take(78) + "…" // Add an ellipsis if the text exceeds 22 characters
                } else {
                    description
                }
                Text(
                    text = truncatedDescription,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

        }
    }
}