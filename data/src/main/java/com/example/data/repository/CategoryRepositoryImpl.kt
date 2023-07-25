package com.example.data.repository

import com.example.data.datasource.ProductDataSource
import com.example.data.db.dao.LikeDao
import com.example.data.db.entity.toLikeProductEntity
import com.example.domain.model.Category
import com.example.domain.model.Product
import com.example.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
    private val likeDao: LikeDao
): CategoryRepository {
    override fun getCategories(): Flow<List<Category>> = flow {
        emit(
            listOf(
                // 예제로는 직접 넣어 주지만 실제로는 서버에서 리스트를 받아와야 한다
                Category.Top, Category.Bag,  Category.Outerwear,
                Category.Dress, Category.FashionAccessories, Category.Pants,
                Category.Skirt, Category.Shoes
            )
        )
    }

    override fun getProductsByCategory(category: Category): Flow<List<Product>> {
        return dataSource.getHomeComponents().map { list ->
            list.filterIsInstance<Product>()
                .filter { product ->
                    product.category.categoryId == category.categoryId
                }
        }.combine(likeDao.getAll()) {products, likeList ->
            products.map { product -> updateLikeStatus(product, likeList.map { it.productId }) }
        }
    }

    override suspend fun likeProduct(product: Product) {
        likeDao.insert(product.toLikeProductEntity())
    }

    private fun updateLikeStatus(product: Product, likeProductIds: List<String>) : Product {
        return product.copy(isLike = likeProductIds.contains(product.productId))
    }
}