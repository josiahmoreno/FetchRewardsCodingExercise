package com.jmoreno.list.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jmoreno.list.app.ui.theme.FetchtestTheme
import com.jmoreno.list.ui.components.FetchListScreen

class JosiahListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchtestTheme {
                FetchListScreen(
                    modifier = Modifier
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun JosiahListScreenPreview() {
    FetchtestTheme {
        FetchListScreen()
    }
}