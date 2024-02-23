package com.example.audiobook.dialog

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

class Dialogs {
    /*
     * 우측 삭제버튼 클릭 시 호출되는 메서드
     * 참고 : https://github.com/android/snippets/blob/2800143cd4547f4c9273251a18769283105337fa/compose/snippets/src/main/java/com/example/compose/snippets/components/Dialog.kt#L223-L264
     */
    @Composable
    fun DeleteDialogExample(
        onDismissRequest: () -> Unit,
        onConfirmation: () -> Unit,
        dialogTitle: String,
        dialogText: String
    ) {
        val context = LocalContext.current
        AlertDialog(
            icon = {
                // 아이콘 설정
                Icon(Icons.Filled.Warning, contentDescription = "Example Icon")
            },
            title = {
                // Dialog 타이틀 설정
                Text(text = dialogTitle)
            },
            text = {
                // 메시지 설정
                Text(text = dialogText)
            },
            onDismissRequest = {
                // 다이얼로그 화면을 벗어났을 경우
                onDismissRequest()
            },
            confirmButton = {
                // 확인 버튼 클릭 시 Listen
                TextButton(
                    onClick = {
                        onConfirmation()
                        Toast.makeText(context, "구독 취소", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text("구독취소")
                }
            },
            dismissButton = {
                // 취소 클릭 시 Listen
                TextButton(
                    onClick = {
                        onDismissRequest()
                        Toast.makeText(context, "돌아가기", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text("돌아가기")
                }
            }
        )
    }
}