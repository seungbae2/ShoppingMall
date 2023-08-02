package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.entity.BasketProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {

    @Query("SELECT * FROM basket")
    fun getAll() : Flow<List<BasketProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: BasketProductEntity)

    @Query("SELECT * FROM basket WHERE productId=:id")
    suspend fun get(id: String): BasketProductEntity?

    @Query("DELETE FROM basket WHERE productId=:id")
    suspend fun delete(id: String)
}