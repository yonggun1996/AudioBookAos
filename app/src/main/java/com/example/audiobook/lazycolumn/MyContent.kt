package com.example.audiobook.lazycolumn

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audiobook.PlayActivity
import com.example.audiobook.R
import com.example.audiobook.dialog.Dialogs
import com.example.audiobook.model.MySubscribeModel

val dialogComponent = Dialogs()

class LazyColumns (activity: Activity){

    val contentList: MutableList<MySubscribeModel> = mutableListOf<MySubscribeModel>()
    var activity: Activity // 화면 전환을 위한 액티비티 변수

    init {
        this.activity = activity
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun mySubscribeList(paddingValues: PaddingValues) {

        contentList.add(MySubscribeModel("나의 캐리어", "전영민", "부크크"))
        contentList.add(MySubscribeModel("비전공자를 위한 이해할 수 있는 IT지식", "최원영", "티더블유아이지"))
        contentList.add(MySubscribeModel("나의 캐리어", "전영민2", "부크크"))
        contentList.add(MySubscribeModel("비전공자를 위한 이해할 수 있는 IT지식2", "최원영", "티더블유아이지"))
        contentList.add(MySubscribeModel("나의 캐리어", "전영민3", "부크크"))
        contentList.add(MySubscribeModel("비전공자를 위한 이해할 수 있는 IT지식3", "최원영", "티더블유아이지"))
        contentList.add(MySubscribeModel("나의 캐리어", "전영민4", "부크크"))
        contentList.add(MySubscribeModel("비전공자를 위한 이해할 수 있는 IT지식4", "최원영", "티더블유아이지"))
        contentList.add(MySubscribeModel("나의 캐리어", "전영민5", "부크크"))
        contentList.add(MySubscribeModel("비전공자를 위한 이해할 수 있는 IT지식5", "최원영", "티더블유아이지"))
        contentList.add(MySubscribeModel("비전공자를 위한 이해할 수 있는 IT지식6", "최원영", "티더블유아이지"))
        contentList.add(MySubscribeModel("나의 캐리어", "전영민6", "부크크"))
        contentList.add(MySubscribeModel("비전공자를 위한 이해할 수 있는 IT지식7", "최원영", "티더블유아이지"))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top, // 상단 헤더영역 아래로 배치하기 위한 로직
        ) {
            items(contentList) { model ->
                ListItem(model = model)
            }
        }
    }

    @Composable
    fun ListItem(model: MySubscribeModel) {
        val openAlertDialog = remember { mutableStateOf(false) }
        Row() {
            val paddingModifier = Modifier.padding(10.dp)

            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                modifier = paddingModifier
            ) {
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
                            .clickable {
                                landingPlayActivity(model)
                            }
                    )
                    // 2024.02.17 작가명 추가
                    Column(
                        modifier = Modifier
                            .weight(8.5f)
                            .clickable {
                                landingPlayActivity(model)
                            }
                    ) {
                        Text(
                            text = model.bookTitle,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(3.dp)
                        )
                        Text(
                            text = model.writerName,
                            fontSize = 12.sp,
                            color = Color.DarkGray,
                            modifier = Modifier.padding(3.dp)
                        )
                    }
                    IconButton(
                        // 해당 버튼 클릭 시 상태 변화 값 변경
                        // dialog을 띄우도록 설정
                        onClick = { openAlertDialog.value = !openAlertDialog.value },
                        modifier = Modifier.weight(1.5f) // 10%
                    ) {
                        Icon(imageVector = Icons.Filled.Delete, contentDescription = "구독 목록에서 삭제")
                    }

                    // openAlertDialog가 클릭 됐을 경우 true 아니면 false
                    when {
                        openAlertDialog.value -> {
                            dialogComponent.DeleteDialogExample(
                                onDismissRequest = { openAlertDialog.value = false },
                                onConfirmation = {
                                    openAlertDialog.value = false
                                    println("Confirmation registered") // Add logic here to handle confirmation.
                                },
                                dialogTitle = "구독 취소 알림",
                                dialogText = "${model.bookTitle}구독을 취소하시겠습니까?",
                            )
                        }
                    }
                }
            }
        }
    }

    fun landingPlayActivity(model: MySubscribeModel) {
        // 컨텐츠 클릭 시
        val intent = Intent(activity, PlayActivity::class.java)
        Log.d("MyContent", "${model.bookTitle}")
        intent.apply {
            action = Intent.ACTION_SEND
            putExtra("bookInfo", model)
        }
        activity.overridePendingTransition(R.anim.slideright, R.anim.slideleft)
        activity.startActivity(intent)
    }
}