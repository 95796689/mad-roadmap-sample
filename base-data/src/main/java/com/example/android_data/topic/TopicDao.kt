package com.example.android_data.topic

import androidx.paging.PagingSource
import androidx.room.*
import com.example.android_data.TopicWithUser
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TopicDao {
    @Transaction
    @Query("SELECT * FROM topic")
    abstract fun entriesObservable(): Flow<List<Topic>>

    @Transaction
    @Query("SELECT * FROM topic WHERE id = :id")
    abstract fun queryTopicWithId(id: Long): Topic

    @Transaction
    @Query("SELECT * FROM topic")
    abstract fun observeTopicWithUser(): Flow<List<TopicWithUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertTopic(topic: Topic)

    @Query("DELETE FROM topic")
    abstract suspend fun deleteAll()

    @Delete
    abstract suspend fun deleteTopic(topic: Topic): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(topics: List<Topic>)
}