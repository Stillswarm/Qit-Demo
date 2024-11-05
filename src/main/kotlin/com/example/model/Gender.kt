package com.example.model

import kotlinx.serialization.Serializable

@Serializable
enum class Gender {
    MALE, FEMALE, OTHER, NONE
}