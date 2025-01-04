package com.jmoreno.list.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.size.Scale
import coil3.size.Size
import com.jmoreno.list.ui.models.EventItemUI

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ItemCard(item: EventItemUI, onItemClick: (EventItemUI) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            //.height(306.dp)
            .padding(
               // top = 4.dp,
              //  bottom = 4.dp,
                start = 16.dp,
                end = 16.dp
            ).clickable {
                onItemClick(item)
            },

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Box(
            Modifier.background(Color.Black.copy(alpha = 0.1f))
        ) {
            AsyncImage(
                model = item.imgSrc,
//                model = ImageRequest.Builder(context = LocalContext.current)
//                    .data(item.imgSrc)
//                    //.size
//                    .scale(Scale.FILL)
//                    .build(),
                //error = painterResource(R.drawable.ic_broken_image),
                //placeholder = painterResource(R.drawable.loading_img),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
            Box(
                Modifier.background(Color.Black.copy(alpha = .25f)).clip(RoundedCornerShape(8.dp)).matchParentSize()
            )
            Column(
                modifier = Modifier.padding(
                    top = 48.dp,
                    bottom = 48.dp,
                    start = 32.dp,
                    end = 32.dp
                )
            ) {
                val headline = item.title
                val truncatedHeadline = if (headline.length > 22) {
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
                val locationLine = if(!item.locationLine2.isEmpty()){
                    item.locationLine1 +"\n"+item.locationLine2
                } else {
                    item.locationLine1
                }
                Text(
                    text = locationLine,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 16.dp)
                )
//                Text(
//                    text = item.locationLine2,
//                    style = MaterialTheme.typography.bodyMedium,
//                    modifier = Modifier.padding(top = 2.dp)
//                )
//                val description = item.description
//                val truncatedDescription = if (description.length > 22) {
//                    description.take(78) + "…" // Add an ellipsis if the text exceeds 78 characters
//                } else {
//                    description
//                }
                Text(
                    //text = truncatedDescription,
                    text = item.description,
                    style = MaterialTheme.typography.bodyLarge,
                  //  fontSize = 20.sp,
                    modifier = Modifier.padding(top = 16.dp)
                        //.background(Color.Red)
                             ,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }

        }
    }
}