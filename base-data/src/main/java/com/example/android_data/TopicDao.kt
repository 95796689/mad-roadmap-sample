package com.example.android_data

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TopicDao {
    @Transaction
    @Query("SELECT * FROM topic")
    abstract fun entriesObservable(): Flow<List<Topic>>

    @Transaction
    @Query("SELECT * from topic WHERE id = :id")
    abstract fun queryTopicWithId(id: Long): Topic

    @Transaction
    @Query("SELECT * FROM topic ORDER BY page ASC, id ASC")
    abstract fun entriesPagingSource(): PagingSource<Int, Topic>

    @Query("DELETE FROM topic WHERE page = :page")
    abstract suspend fun deletePage(page: Int)

    @Query("DELETE FROM topic")
    abstract suspend fun deleteAll()

    @Delete
    abstract suspend fun deleteTopic(topic: Topic): Int

    @Query("SELECT MAX(page) from topic")
    abstract suspend fun getLastPage(): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(topics: List<Topic>)
}