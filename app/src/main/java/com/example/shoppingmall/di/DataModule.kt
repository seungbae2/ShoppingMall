package com.example.shoppingmall.di

import com.example.data.repository.MainRepositoryImpl
import com.example.data.repository.TestRepositoryImpl
import com.example.domain.repository.MainRepository
import com.example.domain.repository.TestRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindTestRepository(testRepository: TestRepositoryImpl) : TestRepository

    @Binds
    @Singleton
    fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl) : MainRepository
}