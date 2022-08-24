package com.example.android_data

val topic = Topic(id = 100L, title = "test title", content = "test content")

internal suspend fun insertTopic(db: SadDatabase) {
    db.topicDao().insertAll(getTopicList())
}

internal fun getTopicList() : List<Topic> {
    val topics = mutableListOf<Topic>()
    topics.add(topic)
    return topics
}