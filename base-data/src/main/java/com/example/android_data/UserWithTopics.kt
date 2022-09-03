package com.example.android_data

import androidx.room.Embedded
import androidx.room.Relation
import com.example.android_data.topic.Topic
import com.example.android_data.user.User

data class UserWithTopics(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userCreatorId"
    )
    val topicList: List<Topic>
)