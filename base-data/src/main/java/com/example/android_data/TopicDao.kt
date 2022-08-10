package com.example.android_data

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TopicDao {
    @Transaction
    @Query("SELECT * FROM topic WHERE page = :page ORDER BY  id ASC")
    abstract fun entriesObservable(page: Int): Flow<List<Topic>>

    @Transaction
    @Query("SELECT * FROM topic ORDER BY page ASC, id ASC")
    abstract fun entriesPagingSource(): PagingSource<Int, Topic>

    @Query("DELETE FROM topic WHERE page = :page")
    abstract suspend fun deletePage(page: Int)

    @Query("DELETE FROM topic")
    abstract suspend fun deleteAll()

    @Query("SELECT MAX(page) from topic")
    abstract suspend fun getLastPage(): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(topics: List<Topic>)
}