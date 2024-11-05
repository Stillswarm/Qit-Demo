package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val businessId: Int,
    val openingTime: Long,
    val closingTime: Long,
    val columns: List<QueueColumn>,
    val category: EventCategory,
    val title: String,
    val description: String,
    val avgWaitTime: Int,
)
