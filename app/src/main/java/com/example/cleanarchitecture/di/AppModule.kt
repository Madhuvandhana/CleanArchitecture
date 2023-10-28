package com.example.cleanarchitecture.di

import android.app.Application
import androidx.room.Room
import com.example.cleanarchitecture.data.local.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val DB_NAME = "maindb.db"

    @Provides
    @Singleton
    fun provideStockDatabase(app: Application): MainDatabase {
        return Room.databaseBuilder(
            app,
            MainDatabase::class.java,
            DB_NAME,
        ).build()
    }
}
