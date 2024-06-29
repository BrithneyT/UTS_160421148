package com.example.hobbyapp160421148.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)