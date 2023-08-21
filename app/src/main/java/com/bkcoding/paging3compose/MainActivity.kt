package com.bkcoding.paging3compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bkcoding.navigation.Navigation
import com.bkcoding.paging3compose.ui.theme.Paging3ComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Paging3ComposeTheme {
                Navigation()
            }
        }
    }
}





