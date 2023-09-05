package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.BasketProduct
import com.example.domain.model.PurchaseHistory
import java.time.ZonedDateTime

@Entity(tableName = "history")
data class PurchaseHistoryEntity(
    val basketList: List<BasketProduct>,
    val purchaseAt: ZonedDateTime
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

fun PurchaseHistoryEntity.toDomainModel() : PurchaseHistory {
    return PurchaseHistory(
        basketList = basketList, purchaseAt = purchaseAt
    )
}

fun PurchaseHistory.toEntity() : PurchaseHistoryEntity {
    return PurchaseHistoryEntity(
        basketList = basketList,
        purchaseAt = purchaseAt
    )
}