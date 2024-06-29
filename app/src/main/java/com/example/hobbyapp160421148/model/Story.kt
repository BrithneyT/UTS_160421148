package com.example.hobbyapp160421148.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "stories")
data class Story(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val author: String,
    val description: String,
    val imageUrl: String,
    val content: String, // Changed from List<String> to String
    val category: String
) : Parcelable
