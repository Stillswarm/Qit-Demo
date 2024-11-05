package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    val name: String,
    val age: Int,
    val gender: Gender,
    val currentQueues: List<Pair<Event, QueueColumn>> = emptyList()
)