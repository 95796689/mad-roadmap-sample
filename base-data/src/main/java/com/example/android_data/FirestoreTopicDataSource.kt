package com.example.android_data

import com.example.android_data.Topic
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


interface TopicDataSource {
    fun getTopic(): Flow<List<Topic>>
    suspend fun addTopic(title: String, content: String): AddTopicStatus
}

class FirestoreTopicDataSource @Inject constructor(
    private val database: DatabaseReference
) : TopicDataSource {

    override fun getTopic(): Flow<List<Topic>> {
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

    override suspend fun addTopic(title: String, content: String): AddTopicStatus {
        return suspendCoroutine { continuation ->
            val topic = Topic(title = title, content = content)
            database.child(TOPIC_COLLECTION).setValue(topic)
                .addOnSuccessListener {
                    continuation.resume(AddTopicSuccess)
                }.addOnFailureListener {
                    continuation.resume(AddTopicFailure(it))
                }
        }
    }

    companion object {
        private const val TOPIC_COLLECTION = "topics"
    }
}