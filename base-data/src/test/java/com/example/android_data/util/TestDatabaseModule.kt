package com.example.android_data.util

import android.content.Context
import androidx.room.Room
import com.example.android_data.SadDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object TestDatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): SadDatabase {
        return Room.inMemoryDatabaseBuilder(context, SadDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }


}