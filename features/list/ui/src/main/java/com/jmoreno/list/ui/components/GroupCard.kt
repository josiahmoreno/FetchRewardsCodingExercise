package com.jmoreno.list.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
//import com.jmoreno.list.ui.models.FetchRewardsGroupUI

//@Composable
//fun GroupCard(data: FetchRewardsGroupUI){
//    Text(
//        text = "Group: ${data.groupId}",
//        modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
//        style = MaterialTheme.typography.headlineMedium
//    )
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 0.dp, bottom = 0.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.surfaceVariant,
//        ), elevation = CardDefaults.elevatedCardElevation()
//    ) {
//        data.items.forEach { item ->
//            Row {
//                Text(text = "ID = ${item.id}")
//                Text(text = "Name = ${item.name}")
//            }
//        }
//    }
//}