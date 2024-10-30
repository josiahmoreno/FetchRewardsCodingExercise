package com.jmoreno.list.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jmoreno.list.app.ui.theme.FetchtestTheme
import com.jmoreno.list.ui.JosiahListScreen

class JosiahListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchtestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JosiahListScreen(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun JosiahListScreenPreview() {
    FetchtestTheme {
        JosiahListScreen("Android")
    }
}