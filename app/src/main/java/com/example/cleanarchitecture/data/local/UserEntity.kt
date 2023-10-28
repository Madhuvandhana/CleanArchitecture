package com.example.cleanarchitecture.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey val id: Int? = null,
    val name: String,
)
