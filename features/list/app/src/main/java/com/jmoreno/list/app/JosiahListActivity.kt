package com.jmoreno.list.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jmoreno.list.app.ui.theme.PhunAppListTheme
import com.jmoreno.list.ui.components.EventsListScreen

class JosiahListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(
            Color.White.copy(alpha = .1f).toArgb()
        ))
        setContent {
            PhunAppListTheme {
                EventsListScreen(
                    appName = stringResource(R.string.app_name,),
                    placeHolder = painterResource(R.drawable.placeholder_nomoon)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun JosiahListScreenPreview() {
    PhunAppListTheme {
        EventsListScreen(appName = stringResource(R.string.app_name), placeHolder = painterResource(R.drawable.placeholder_nomoon))
    }
}