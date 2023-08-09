package com.example.domain.usecase

import com.example.domain.model.BasketProduct
import com.example.domain.model.Product
import com.example.domain.repository.BasketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BasketUseCase @Inject constructor(
    private val basketRepository: BasketRepository
){
    fun getBasketProducts() : Flow<List<BasketProduct>> {
        return basketRepository.getBasketProducts()
    }

    suspend fun removeBasketProducts(product: Product) {
        basketRepository.removeBasketProduct(product = product)
    }
}