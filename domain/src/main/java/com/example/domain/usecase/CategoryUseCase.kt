package com.example.domain.usecase

import com.example.domain.model.Category
import com.example.domain.model.Product
import com.example.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    fun getCategories() : Flow<List<Category>> {
        return repository.getCategories()
    }

    fun getProductsByCategory(category: Category): Flow<List<Product>> {
        return repository.getProductsByCategory(category)
    }
}