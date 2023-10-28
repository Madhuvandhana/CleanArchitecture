package com.example.cleanarchitecture.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Query("DELETE FROM UserEntity where id = :id")
    suspend fun deleteUser(id: Int)

    @Query("SELECT * FROM UserEntity")
    suspend fun getUsers(): List<UserEntity>
}
