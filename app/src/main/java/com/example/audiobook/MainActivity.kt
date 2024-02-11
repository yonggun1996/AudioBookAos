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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audiobook.model.MySubscribeModel
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
                    SetActivity()
                }
            }
        }
    }
}

// 콘텐츠 구현
// TopAppBar : https://developer.android.com/jetpack/compose/components/app-bars?hl=ko
// LazyColumn : https://makb.medium.com/listview-recyclerview-using-android-jetpack-compose-lazycolumn-ad905f52bf09
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
        // LazyColumn 데이터 삽입
        val contentList: MutableList<MySubscribeModel> = mutableListOf<MySubscribeModel>()

        contentList.add(MySubscribeModel("나의 캐리어", "전영민", "부크크"))
        contentList.add(MySubscribeModel("비전공자를 위한 이해할 수 있는 IT지식", "최원영", "티더블유아이지"))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top, // 상단 헤더영역 아래로 배치하기 위한 로직
        ) {
            items(contentList) { model ->
                ListItem(model = model)
            }
        }
    }
}

/*
 * 리스트 요소 setting
 */
@Composable
fun ListItem(model: MySubscribeModel) {
    Row() {
        val paddingModifier = Modifier.padding(10.dp)
        Card(elevation = CardDefaults.cardElevation(defaultElevation = 10.dp), modifier = paddingModifier) {
            Row(
                // 수평 폭 기기 크기에 맞게 세팅
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // url로 이미지를 가져와야 하는 경우
                // https://developer.android.com/jetpack/compose/graphics/images/loading?hl=ko
                Image(
                    painter = painterResource(R.drawable.book_icon),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(5.dp)
                )
                Text(
                    text = model.bookTitle,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
            }
        }
    }
}