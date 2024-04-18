package com.example.hobbyapp160421148.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Story(
    val title: String,
    val author: String,
    val description:String,
    val imageUrl: String,
    val content: List<String>
) : Parcelable