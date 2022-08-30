package com.example.user_auth

sealed class AuthState
object AuthStateSuccess: AuthState()
data class AuthStateFailure(var throwable: Throwable?): AuthState()