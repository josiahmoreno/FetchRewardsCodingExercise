package com.jmoreno.list.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jmoreno.list.ui.models.FetchRewardsGroupUI
import com.jmoreno.list.ui.models.FetchRewardsItemUI

@Composable
fun LazyItemScope.ItemCard(item: FetchRewardsItemUI){
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