package com.example.android_data

import com.example.android_data.topic.Topic
import com.example.android_data.user.UserUtils
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


interface TopicDataSource {
    fun getTopic(): Flow<TopicWithUser>
    suspend fun addTopic(title: String, content: String): AddTopicStatus
}

class FirestoreTopicDataSource @Inject constructor(
    private val database: DatabaseReference
) : TopicDataSource {

    override fun getTopic(): Flow<TopicWithUser> {
        return callbackFlow {
            val task = database.child(TOPIC_COLLECTION)
            val topicListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Get Post object and use the values to update the UI
                    for (postSnapshot in dataSnapshot.children) {
                        val topicWithUser = postSnapshot.getValue(TopicWithUser::class.java)
                        topicWithUser?.let {
                            trySend(it)
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Timber.w("loadTopic:onCancelled", databaseError.toException())
                }
            }
            task.addValueEventListener(topicListener)
            awaitClose { task.removeEventListener(topicListener) }
        }
    }

    override suspend fun addTopic(title: String, content: String): AddTopicStatus {
        return suspendCoroutine { continuation ->
            val me = UserUtils.getMeAsUser()
            me?.let { user ->
                val topic = Topic(title = title, content = content, userCreatorId = user.userId)
                val topicWithUser = TopicWithUser(topic = topic, user = user)
                val key = database.child(TOPIC_COLLECTION).push().key
                database.child(TOPIC_COLLECTION).child(key!!).setValue(topicWithUser)
                    .addOnSuccessListener {
                        continuation.resume(AddTopicSuccess)
                    }.addOnFailureListener {
                        continuation.resume(AddTopicFailure(it))
                    }
            }
        }
    }

    companion object {
        private const val TOPIC_COLLECTION = "topics"
    }
}