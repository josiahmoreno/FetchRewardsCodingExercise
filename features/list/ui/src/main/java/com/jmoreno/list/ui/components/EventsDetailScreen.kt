package com.jmoreno.list.ui.components

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.jmoreno.list.ui.models.EventItemUI


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun EventsDetailScreen(eventItemUI: EventItemUI, onBackArrowPressed: () -> Unit) {
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val context = LocalContext.current
    var permissionGranted by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        permissionGranted = isGranted
        if (isGranted) {
            // If permission is granted, proceed with the call
            val callIntent = Intent(Intent.ACTION_CALL).apply {
                data = Uri.parse("tel:${eventItemUI.phone}")
            }
            context.startActivity(callIntent)
        }
    }

    LaunchedEffect(Unit) {
        // Check if permission is already granted
        permissionGranted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED

    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Box(modifier = Modifier) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(eventItemUI.imgSrc) // Replace with your image URL
                        .crossfade(true)
                        .build(),
                    contentDescription = "Background Image",
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop
                )
                LargeTopAppBar(
                    modifier = Modifier,
                    title = {},
                    expandedHeight = 300.dp,
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Transparent,
                        scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onBackground,
                    ),
                    scrollBehavior = scrollBehavior,
                    navigationIcon = {
                        IconButton(onClick = { /* Handle back action */
                            onBackArrowPressed()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                        if (eventItemUI.phone != null) {
                            IconButton(onClick = {
                                if (permissionGranted) {
                                    // Make the call
                                    val callIntent = Intent(Intent.ACTION_CALL).apply {
                                        data = Uri.parse("tel:${eventItemUI.phone}")
                                    }
                                    context.startActivity(callIntent)
                                } else {
                                    // Request permission
                                    permissionLauncher.launch(Manifest.permission.CALL_PHONE)
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Phone,
                                    contentDescription = "Phone",
                                    tint = Color.White
                                )
                            }
                        }

                        IconButton(onClick = {
                            val smsIntent = Intent(Intent.ACTION_SENDTO).apply {
                                data = Uri.parse("smsto:")
                                putExtra(
                                    "sms_body", "Check out this event! ${eventItemUI.title}\n" +
                                            eventItemUI.dateOfEventFormatted.date
                                ) // Add the message body
                            }
                            val emailIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "message/rfc822"
                                putExtra(
                                    Intent.EXTRA_SUBJECT,
                                    "Check out this event! ${eventItemUI.title}"
                                ) // Subject
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "${eventItemUI.title}\n\n${eventItemUI.dateOfEventFormatted.date}"
                                ) // Body
                            }
                            val chooserIntent =
                                Intent.createChooser(emailIntent, "Share via").apply {
                                    putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(smsIntent))
                                }
                            context.startActivity(chooserIntent)

                        }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Share",
                                tint = Color.White
                            )
                        }
                    })
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(
                    start = 16.dp,
                    top = 0.dp,
                    end = 16.dp
                )
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = eventItemUI.dateOfEventFormatted.date,
                modifier = Modifier.padding(top = 24.dp),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = eventItemUI.title,
                modifier = Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.headlineMediumEmphasized,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = eventItemUI.locationLine1,
                modifier = Modifier.padding(top = 18.dp),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = eventItemUI.locationLine2,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold

            )
            Text(
                text = eventItemUI.description,
                modifier = Modifier.padding(top = 16.dp)
            )
        }


    }
}
