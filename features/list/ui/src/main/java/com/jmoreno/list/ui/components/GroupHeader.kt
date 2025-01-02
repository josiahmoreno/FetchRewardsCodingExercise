package com.jmoreno.list.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
//import com.jmoreno.list.ui.models.FetchRewardsGroupUI

//@Composable
//fun LazyItemScope.GroupHeader(group: FetchRewardsGroupUI){
//    Box(
//        modifier = Modifier
//            .background(MaterialTheme.colorScheme.tertiaryContainer)
//            .fillParentMaxWidth()
//    ) {
//        Text(
//            "Group ${group.groupId}",
//            modifier = Modifier.padding(
//                top = 16.dp,
//                start = 16.dp,
//                end = 16.dp,
//                bottom = 8.dp
//            ),
//            style = MaterialTheme.typography.headlineMedium,
//            color = MaterialTheme.colorScheme.onTertiaryContainer
//        )
//    }
//}