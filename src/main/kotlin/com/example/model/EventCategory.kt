package com.example.model

import kotlinx.serialization.Serializable

@Serializable
enum class EventCategory {
    ENTERTAINMENT, SPORTS, ECOMMERCE, MEDICAL, OTHER
}