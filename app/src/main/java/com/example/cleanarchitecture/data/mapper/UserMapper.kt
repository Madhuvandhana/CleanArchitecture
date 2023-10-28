package com.example.cleanarchitecture.data.mapper

import com.example.cleanarchitecture.data.local.UserEntity
import com.example.cleanarchitecture.domain.model.User

fun UserEntity.toUserModel(): User {
    return User(
        name = name,
    )
}
