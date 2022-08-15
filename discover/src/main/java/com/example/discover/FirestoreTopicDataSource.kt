package com.example.discover

import com.example.android_data.Topic
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import javax.inject.Inject

typealias TopicMap = Map<String, List<Topic>>

class FirestoreTopicDataSource @Inject constructor(
    private val database: DatabaseReference
)  {

    fun getTopic(): Flow<List<Topic>> {
        addTopic("test1", "hello")

        return callbackFlow {
            val task = database.child(TOPIC_COLLECTION)
            val topicListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Get Post object and use the values to update the UI
                    val topics = mutableListOf<Topic>()
                    val topic = dataSnapshot.getValue(Topic::class.java)
                    topic?.let {
                        topics.add(it)
                        trySend(topics)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Timber.w("loadTopic:onCancelled", databaseError.toException())
                }
            }
            task.addListenerForSingleValueEvent(topicListener)
            awaitClose { task.removeEventListener(topicListener) }
        }
    }

    private fun addTopic(title: String, content: String) {
        val topic = Topic(title = title, content = content)
        database.child(TOPIC_COLLECTION).setValue(topic)
    }

    companion object {
        private const val TOPIC_COLLECTION = "topics"
    }
}