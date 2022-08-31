package com.example.android_data

import java.lang.Exception

sealed class AddTopicStatus
object AddTopicSuccess: AddTopicStatus()
class AddTopicFailure(var exception: Exception): AddTopicStatus()