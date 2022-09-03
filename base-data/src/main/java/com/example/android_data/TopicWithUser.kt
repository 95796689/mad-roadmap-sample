package com.example.android_data

import androidx.room.Embedded
import androidx.room.Relation
import com.example.android_data.topic.Topic
import com.example.android_data.user.User

data class TopicWithUser(
    @Embedded val topic: Topic? = null,
    @Relation(
        parentColumn = "userCreatorId",
        entityColumn = "userId"
    )
    val user: User? = null
)