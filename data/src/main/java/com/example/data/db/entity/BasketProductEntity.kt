package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.db.converter.BasketConverter
import com.example.domain.model.*

@Entity("basket")
@TypeConverters(BasketConverter::class)
data class BasketProductEntity(
    @PrimaryKey
    val productId: String,
    val productName: String,
    val imageUrl: String,
    val price: Price,
    val category: Category,
    val shop: Shop,
    val isNew: Boolean,
    val isFreeShipping: Boolean,
    val isLike: Boolean,
    val count: Int,
)

fun BasketProductEntity.toDomainModel() : Product {
    return Product(
        productId = productId,
        productName = productName,
        imageUrl = imageUrl,
        price = price,
        category = category,
        shop = shop,
        isNew = isNew,
        isFreeShipping = isFreeShipping,
        isLike = isLike
    )
}
fun Product.toBasketProductEntity() : BasketProductEntity{
    return BasketProductEntity(
        productId = productId,
        productName = productName,
        imageUrl = imageUrl,
        price = price,
        category = category,
        shop = shop,
        isNew = isNew,
        isFreeShipping = isFreeShipping,
        isLike = isLike,
        count = 1
    )
}