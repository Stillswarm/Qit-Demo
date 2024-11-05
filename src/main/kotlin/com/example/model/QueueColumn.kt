package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class QueueColumn(
    val title: String,
    val maxLimit: Int,
    val customers: List<Customer>
)
