package com.example.android_data

import com.example.android_data.topic.Topic
import com.example.android_data.user.User

val testTopic = Topic(id = 100L, title = "test title", content = "test content", userCreatorId = "userid")
val testUser = User(userId = "userid", name = "name")

internal suspend fun insertTopic(db: SadDatabase) {
    db.topicDao().insertTopic(testTopic)
}

internal fun getTopic(): Topic {
    return testTopic
}

internal fun getTopicWithUser() : TopicWithUser {
    return TopicWithUser(topic = testTopic, user = testUser)
}