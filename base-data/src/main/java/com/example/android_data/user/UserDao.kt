package com.example.android_data.user

import androidx.room.*
import com.example.android_data.UserWithTopics
import com.example.android_data.topic.Topic
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao {
    @Query("SELECT * FROM user")
    abstract fun observeUser(): Flow<User>

    @Transaction
    @Query("SELECT * FROM user WHERE userId = :userId")
    abstract suspend fun getUserWithTopics(userId: String): UserWithTopics

    @Query("SELECT * FROM user WHERE userId = :userId")
    abstract suspend fun getUser(userId: String): User

    @Query("DELETE FROM user WHERE userId = :userId")
    abstract suspend fun deleteWithUsername(userId: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertUser(user: User)

    @Query("DELETE FROM user")
    abstract suspend fun deleteAll()
}