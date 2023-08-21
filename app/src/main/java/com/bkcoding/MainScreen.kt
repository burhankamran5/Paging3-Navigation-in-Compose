package com.bkcoding

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.request.ImageRequest
import com.bkcoding.paging3compose.data.model.BeerDto
import com.bkcoding.paging3compose.mappers.toBeerData
import com.bkcoding.paging3compose.viewmodel.BeerViewModel
import com.google.accompanist.coil.rememberCoilPainter
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    val viewModel: BeerViewModel = hiltViewModel()
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Paging 3") })
    }, content = {
        Paging(viewModel, navController)
    })

}


@Composable
fun Paging(viewModel: BeerViewModel, navController: NavController) {

    val pagingItems = viewModel.pagingData.collectAsLazyPagingItems()

    LazyColumn {

        if (pagingItems.loadState.refresh is LoadState.Loading) {
            item {
                Box(modifier = Modifier.fillParentMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }


        if (pagingItems.loadState.refresh is LoadState.NotLoading) {
            items(pagingItems.itemCount) { index ->
                val item = pagingItems[index]
                item?.let {
                    ListItem(beer = it, navController)
                }
            }

        }

        if (pagingItems.loadState.append is LoadState.Loading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

        }

        if (pagingItems.loadState.refresh is LoadState.Error) {
            item {
                ErrorFooter()
            }
        }

        if (pagingItems.loadState.append is LoadState.Error) {
            item {
                ErrorFooter()
            }
        }

    }
}

@Composable
fun ErrorFooter(retry: () -> Unit = {}) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(imageVector = Icons.Default.Warning, contentDescription = null)
            Text(
                text = "Error Occurred",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Retry",
                    modifier = Modifier
                        .clickable { retry.invoke() }
                        .align(Alignment.CenterEnd),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }

}


@Composable
fun ListItem(beer: BeerDto, navController: NavController) {

    Box(modifier = Modifier
        .border(width = 2.dp, color = Color.Blue)
        .clickable {
            val product = beer.toBeerData()
            Log.d("image_url Before", "${product.image_url} ")
            val encodedUrl = URLEncoder.encode(product.image_url, StandardCharsets.UTF_8.toString())
            Log.d("image_url after", "$encodedUrl")
//            Log.d("DetailScreen", "ListItem: DetailScreen/${beer.name}/${beer.description}/${beer.image_url}")
            navController.navigate("DetailScreen/${product.name}/${product.description}/${encodedUrl}")
        }) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp),
                painter = rememberCoilPainter(
                    request = ImageRequest.Builder(LocalContext.current).crossfade(true)
                        .data(beer.image_url).build()
                ), contentDescription = ""
            )

            Text(
                text = "Name: " + beer.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(8.dp),
                fontSize = 15.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "Desc: " + beer.description,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(8.dp),
                fontSize = 10.sp,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Light
            )
        }
    }

}