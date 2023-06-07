package com.example.data.repository

import com.example.data.datasource.TestDataSource
import com.example.data.model.toDomainModel
import com.example.domain.model.TestModel
import com.example.domain.repository.TestRepository

class TestRepositoryImpl(private val dataSource: TestDataSource) : TestRepository {
    override fun getTestData(): TestModel? {
        return dataSource.getTestModelResponse().toDomainModel()
    }
}