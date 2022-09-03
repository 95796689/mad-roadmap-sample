package com.example.android_data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_data.topic.Topic
import com.example.android_data.topic.TopicDao
import com.example.android_data.user.User
import com.example.android_data.user.UserDao

@Database(entities = [Topic::class, User::class], version = 1)
abstract class SadDatabase : RoomDatabase() {
    abstract fun topicDao(): TopicDao
    abstract fun userDao(): UserDao
}