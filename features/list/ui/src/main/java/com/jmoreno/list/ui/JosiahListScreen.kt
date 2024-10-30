package com.jmoreno.list.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun JosiahListScreen(modifier: Modifier = Modifier, viewModel: ListViewModel = koinViewModel()) {
    val state = viewModel.viewState.collectAsState()
    LazyColumn(modifier = modifier) {
        items(state.value.data){ data ->
            Text(text = "Group: ${data.groupTitle}")
            data.items.forEach { item ->
                Row {
                    Text(text = "ID = ${item.id}")
                    Text(text = "Name = ${item.name}")
                }
            }
        }
    }
}