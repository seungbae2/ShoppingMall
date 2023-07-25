package com.example.domain.usecase

import com.example.domain.model.BaseModel
import com.example.domain.model.Product
import com.example.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainUseCase @Inject constructor(private val mainRepository: MainRepository) {

    fun getModelList() : Flow<List<BaseModel>> {
        return mainRepository.getModelList()
    }

    suspend fun likeProduct(product: Product) {
        mainRepository.likeProduct(product)
    }
}