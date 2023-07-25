package com.example.domain.usecase

import com.example.domain.model.Product
import com.example.domain.model.SearchFilter
import com.example.domain.model.SearchKeyword
import com.example.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    suspend fun search(keyword: SearchKeyword, filters: List<SearchFilter>) : Flow<List<Product>> {
        return searchRepository.search(keyword, filters)
    }

    fun getSearchKeywords() : Flow<List<SearchKeyword>> {
        return searchRepository.getSearchKeywords()
    }

    suspend fun likeProduct(product: Product) {
        searchRepository.likeProduct(product)
    }
}