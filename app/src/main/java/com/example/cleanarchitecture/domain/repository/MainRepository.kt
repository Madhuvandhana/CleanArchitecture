package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.domain.model.User
import com.example.cleanarchitecture.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getUsers(): Flow<Resource<List<User>>>
}
