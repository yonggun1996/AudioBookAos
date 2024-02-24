@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.audiobook

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audiobook.lazycolumn.LazyColumns
import com.example.audiobook.model.MySubscribeModel
import com.example.audiobook.ui.theme.AudioBookTheme

class MainActivity : ComponentActivity() {

    val lazyColumnComponent = LazyColumns(this@MainActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudioBookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetActivity()
                }
            }
        }
    }

    // 콘텐츠 구현
// TopAppBar : https://developer.android.com/jetpack/compose/components/app-bars?hl=ko
// LazyColumn : https://makb.medium.com/listview-recyclerview-using-android-jetpack-compose-lazycolumn-ad905f52bf09
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun SetActivity() {
        Scaffold (
            topBar = { // 헤더 구현
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
            // 나의 구독 목록 호출
            lazyColumnComponent.mySubscribeList(it)
        }
    }

    @Preview
    @Composable
    fun PreviewGreeting() {
        SetActivity()
    }
}

