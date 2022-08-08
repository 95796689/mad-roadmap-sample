package com.example.user_auth

import android.content.SharedPreferences
import com.example.base_android.AppCoroutineDispatchers
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@OptIn(DelicateCoroutinesApi::class)
@Singleton
class UserAuthManager @Inject constructor(
    private val dispatchers: AppCoroutineDispatchers,
    @Named("auth") private val authPrefs: SharedPreferences,
) {

}