package com.example.user_auth

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AccountModule {
    @Binds
    abstract fun provideAccountService(binds: AccountServiceImp): AccountService
}