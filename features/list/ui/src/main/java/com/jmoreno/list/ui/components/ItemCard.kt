package com.jmoreno.list.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jmoreno.list.ui.models.EventItemUI

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ItemCard(item: EventItemUI,
             onItemClick: (EventItemUI) -> Unit,
            placeHolder : Painter
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
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
                fallback = placeHolder,
                placeholder = placeHolder,
                error = placeHolder,
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
                //  Add an ellipsis if the text exceeds 22 characters
                //
                val truncatedHeadline = if (headline.length > 22) {
                    headline.take(22) + "…"
                } else {
                    headline
                }
                Text(text = item.dateOfEventFormatted.date)
                Text(
                    text = truncatedHeadline,
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.headlineMedium
                )
                val locationLine = if(item.locationLine2.isNotEmpty()){
                    item.locationLine1 +"\n"+item.locationLine2
                } else {
                    item.locationLine1
                }
                Text(
                    text = locationLine,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 16.dp)
                )
                val description = item.description
                val truncatedDescription = if (description.length > 78) {
                    description.take(78) + "…"
                } else {
                    description
                }
                Text(
                    text = truncatedDescription,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 16.dp),
                )
            }

        }
    }
}