package com.example.base_android.inject

import com.example.base_android.util.Analytics
import com.example.base_android.util.SadAnalytics
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BaseModule {
    @Singleton
    @Binds
    internal abstract fun provideAnalytics(binds: SadAnalytics): Analytics
}