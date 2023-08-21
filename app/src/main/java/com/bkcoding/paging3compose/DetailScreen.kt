package com.bkcoding.paging3compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.request.ImageRequest
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun DetailScreen(
    navController: NavController,
    name: String? = null,
    description: String? = null,
    url: String? = null
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp),
            painter = rememberCoilPainter(
                request = ImageRequest.Builder(LocalContext.current).crossfade(true)
                    .data(url).build()
            ), contentDescription = ""
        )
        Text(
            text = name.toString(),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        Text(text = description.toString())
    }


}