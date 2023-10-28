package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.data.repository.MainRepositoryImpl
import com.example.cleanarchitecture.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        gatedSystemRepositoryImpl: MainRepositoryImpl,
    ): MainRepository
}
