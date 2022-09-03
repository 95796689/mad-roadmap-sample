package com.example.android_data.topic

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity
data class Topic(
    @PrimaryKey(autoGenerate = true) val id: Long = Random.nextLong(),
    val title: String = "",
    val content: String = "",
    val userCreatorId: String = ""
)