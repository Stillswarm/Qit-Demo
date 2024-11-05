package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val password: String,
    val phoneNo: Long,
    val businessId: Int? = null,
    val customerId: Int? = null
)