package com.example.shoppingmall.di

import com.example.data.repository.TestRepositoryImpl
import com.example.domain.repository.TestRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindTestRepository(testRepository: TestRepositoryImpl) : TestRepository
}