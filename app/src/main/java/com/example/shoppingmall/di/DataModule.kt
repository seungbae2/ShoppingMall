package com.example.shoppingmall.di

import com.example.data.repository.AccountRepositoryImpl
import com.example.data.repository.BasketRepositoryImpl
import com.example.data.repository.CategoryRepositoryImpl
import com.example.data.repository.LikeRepositoryImpl
import com.example.data.repository.MainRepositoryImpl
import com.example.data.repository.ProductDetailRepositoryImpl
import com.example.data.repository.SearchRepositoryImpl
import com.example.domain.repository.AccountRepository
import com.example.domain.repository.BasketRepository
import com.example.domain.repository.CategoryRepository
import com.example.domain.repository.LikeRepository
import com.example.domain.repository.MainRepository
import com.example.domain.repository.ProductDetailRepository
import com.example.domain.repository.SearchRepository
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
    fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl) : MainRepository

    @Binds
    @Singleton
    fun bindCategoryRepository(categoryRepositoryImpl: CategoryRepositoryImpl) : CategoryRepository

    @Binds
    @Singleton
    fun bindProductDetailRepository(productDetailRepositoryImpl: ProductDetailRepositoryImpl) : ProductDetailRepository

    @Binds
    @Singleton
    fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl) : SearchRepository

    @Binds
    @Singleton
    fun bindAccountRepository(accountRepository: AccountRepositoryImpl) : AccountRepository

    @Binds
    @Singleton
    fun bindLikeRepository(likeRepositoryImpl: LikeRepositoryImpl) : LikeRepository

    @Binds
    @Singleton
    fun bindBasketRepository(basketRepositoryImpl: BasketRepositoryImpl) : BasketRepository
}