@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.audiobook

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.audiobook.ui.theme.AudioBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudioBookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopAppBar()
                }
            }
        }
    }
}

// 상단 헤더 구현
// https://developer.android.com/jetpack/compose/components/app-bars?hl=ko
@Composable
fun TopAppBar() {
    Scaffold (
        topBar = {
            val context = LocalContext.current
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                title = {
                    Text("관심도서")
                },
                actions = {
                    IconButton(
                        onClick = {
                            Toast.makeText(context, "검색버튼 클릭", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "도서검색 아이콘"
                        )
                    }
                    IconButton(
                        onClick = {
                            Toast.makeText(context, "마이페이지 클릭", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = "마이페이지 아이콘"
                        )
                    }
                }
            )
        }
    ) {

    }
}
