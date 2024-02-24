package com.example.audiobook

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.audiobook.model.MySubscribeModel
import com.example.audiobook.ui.theme.AudioBookTheme

class PlayActivity : ComponentActivity() {

    lateinit var bookInfo: MySubscribeModel // 메인 페이지에서 전달한 책 정소
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bookInfo = intent.getSerializableExtra("bookInfo") as MySubscribeModel
        setContent {
            AudioBookTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetActivity()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun SetActivity() {
        Scaffold (
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = bookInfo.bookTitle)
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                finish()
                            }
                        ) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "메인 페이지 이동")
                        }
                    }
                )
            }
        ) {

        }
    }

    @Preview
    @Composable
    fun PreviewGreeting() {
        SetActivity()
    }
}