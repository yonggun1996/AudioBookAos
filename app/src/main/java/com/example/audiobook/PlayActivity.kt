package com.example.audiobook

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.exoplayer.ExoPlayer
import com.example.audiobook.model.MySubscribeModel
import com.example.audiobook.ui.theme.AudioBookTheme
import kotlinx.coroutines.delay

class PlayActivity : ComponentActivity() {

    lateinit var bookInfo: MySubscribeModel // 메인 페이지에서 전달한 책 정보
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bookInfo = intent.getSerializableExtra("bookInfo") as MySubscribeModel
//        exoPlayer = ExoPlayer.Builder(this).build()
        setContent {
            AudioBookTheme {
                Surface {
                    SetActivity()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun SetActivity() {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green)
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = bookInfo.bookTitle,
                Modifier.padding(bottom = 15.dp),
                fontSize = 30.sp
            )
            Text(
                text = bookInfo.writerName,
                Modifier.padding(bottom = 15.dp),
                fontSize = 20.sp
            )
            Image(
                painter = painterResource(id = R.drawable.book_icon),
                contentDescription = "콘텐츠 대표 이미지",
                Modifier.padding(bottom = 15.dp)
            )
            Text(
                text = "책 구절1",
                Modifier.padding(bottom = 10.dp),
                fontSize = 15.sp
            )
            Text(
                text = "책 구절2",
                Modifier.padding(bottom = 10.dp),
                fontSize = 15.sp
            )
            MusicPlayerSlide()
        }
    }

    @Composable
    fun MusicPlayerSlide() {
        val exoPlayer = ExoPlayer.Builder(LocalContext.current).build()

        // 현재 음성파일이 실행중인지 저장하는 변수
        val isPlaying = remember {
            mutableStateOf(false)
        }

        // 현재 재생되는 시간
        val currentPosition = remember {
            mutableLongStateOf(0)
        }

        // 슬라이더를 끌어당기는 시간
        val sliderPosition = remember {
            mutableLongStateOf(0)
        }

        // 총 시간
        val totalDuration = remember {
            mutableLongStateOf(0)
        }

        LaunchedEffect(key1 = exoPlayer.currentPosition, key2 = exoPlayer.isPlaying) {
            delay(1000)
            currentPosition.longValue = exoPlayer.currentPosition
        }

        LaunchedEffect(currentPosition.longValue) {
            sliderPosition.longValue = currentPosition.longValue
        }

        LaunchedEffect(exoPlayer.duration) {
            if (exoPlayer.duration > 0) {
                totalDuration.longValue = exoPlayer.duration
            }
        }

        // 음성파일 슬라이더
        Slider(
            value = sliderPosition.longValue.toFloat(),
            onValueChange = {
                sliderPosition.longValue = it.toLong()
            },
            onValueChangeFinished = {
                currentPosition.longValue = sliderPosition.longValue
                exoPlayer.seekTo(sliderPosition.longValue)
            },
            valueRange = 0f .. totalDuration.longValue.toFloat(),
            colors = SliderDefaults.colors(
                thumbColor = Color.Black,
                activeTrackColor = Color.DarkGray,
                inactiveTrackColor = Color.Gray,
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {

            Text(
                text = (currentPosition.longValue).convertToText(),
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                color = Color.Black,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )

            val remainTime = totalDuration.longValue - currentPosition.longValue
            Text(
                text = if (remainTime >= 0) remainTime.convertToText() else "",
                modifier = Modifier
                    .padding(8.dp),
                color = Color.Black,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }
    }

    private fun Long.convertToText(): String {
        val sec = this / 1000
        val minutes = sec / 60
        val seconds = sec % 60

        val minutesString = if (minutes < 10) {
            "0$minutes"
        } else {
            minutes.toString()
        }
        val secondsString = if (seconds < 10) {
            "0$seconds"
        } else {
            seconds.toString()
        }
        return "$minutesString:$secondsString"
    }

    @Preview
    @Composable
    fun PreviewGreeting() {
        SetActivity()
    }
}