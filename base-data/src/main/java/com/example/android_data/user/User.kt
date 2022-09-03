package com.example.android_data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val userId: String = "",
    val name: String? = ""
)