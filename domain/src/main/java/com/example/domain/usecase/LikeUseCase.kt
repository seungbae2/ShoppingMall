package com.example.domain.usecase

import com.example.domain.model.Product
import com.example.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LikeUseCase @Inject constructor(
    private val repository: LikeRepository
){

    fun getLikeProducts(): Flow<List<Product>> {
        return repository.getLikeProduct()
    }
}