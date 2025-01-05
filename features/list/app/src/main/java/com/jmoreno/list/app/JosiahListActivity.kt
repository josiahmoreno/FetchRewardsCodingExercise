package com.jmoreno.list.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.alpha
import androidx.core.view.WindowCompat
import com.jmoreno.list.app.ui.theme.FetchtestTheme
import com.jmoreno.list.ui.components.EventsListScreen
import androidx.activity.compose.BackHandler
import androidx.compose.ui.res.painterResource
import com.jmoreno.list.ui.models.EventItemUI

class JosiahListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        //WindowCompat.setDecorFitsSystemWindows(window, true)
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(
            //android.graphics.Color.parseColor("#801b1b1b")
            Color.White.copy(alpha = .1f).toArgb()
        ))
        setContent {
            FetchtestTheme {
                EventsListScreen(
                    modifier = Modifier,
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
    FetchtestTheme {
        EventsListScreen(appName = stringResource(R.string.app_name), placeHolder = painterResource(R.drawable.placeholder_nomoon))
    }
}