package com.jmoreno.list.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun JosiahListScreen(name: String, modifier: Modifier = Modifier, viewModel: ListViewModel = koinViewModel()) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}