package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Business(
    val name: String,
    val address: String,
    val category: EventCategory,
    val currentEvents: List<Event> = emptyList(),
    val lastClosedEvent: Event?
)
