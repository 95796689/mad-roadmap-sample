package com.example.android_data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Topic::class], version = 1)
abstract class SadDatabase : RoomDatabase() {
    abstract fun topicDao(): TopicDao
}