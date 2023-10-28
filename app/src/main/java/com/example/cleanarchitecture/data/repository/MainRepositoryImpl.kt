package com.example.cleanarchitecture.data.repository

import android.database.sqlite.SQLiteException
import com.example.cleanarchitecture.data.local.MainDatabase
import com.example.cleanarchitecture.data.mapper.toUserModel
import com.example.cleanarchitecture.data.util.Resource
import com.example.cleanarchitecture.domain.model.User
import com.example.cleanarchitecture.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(
    private val db: MainDatabase,
) : MainRepository {

    private val dao = db.dao
    override suspend fun getUsers(): Flow<Resource<List<User>>> = flow {
        emit(Resource.Loading(true))
        try {
            val users = dao.getUsers()
            emit(
                Resource.Success(
                    data = users.map { it.toUserModel() },
                ),
            )
        } catch (e: SQLiteException) {
            Resource.Error(
                message = "Couldn't load users data",
                data = null,
            )
        }
    }
}
