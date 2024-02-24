package com.example.audiobook.model

import java.io.Serializable

/*
 * 개인 관심 도서 목록 Model
 * bookTitle : 책 제목
 * writerName : 작가명
 * publisher : 출판사
 */
data class MySubscribeModel (
    val bookTitle: String,
    val writerName: String,
    val publisher: String
) : Serializable