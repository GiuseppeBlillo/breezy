package com.example.nuuuuu

import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.nuuuuu.ui.theme.NuuuuuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen()
        }
    }
}

@Composable
fun MainActivityScreen() {
    NuuuuuTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AndroidView(
                factory = {
                        context ->
                    val videoView = VideoView(context)
                    videoView.setOnTouchListener { _, motionEvent ->
                        when (motionEvent.action) {
                            MotionEvent.ACTION_DOWN -> {
                                if (!videoView.isPlaying) {
                                    videoView.seekTo(0)
                                    val videoUri = Uri.parse("android.resource://${context.packageName}/raw/breezy")
                                    videoView.setVideoURI(videoUri)
                                    videoView.start()
                                }
                            }
                        }
                        true
                    }
                    videoView
                          },
                modifier = Modifier.fillMaxSize().padding(20.dp)
            )
        }
    }
}
