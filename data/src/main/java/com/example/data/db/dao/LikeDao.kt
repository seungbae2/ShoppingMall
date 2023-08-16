package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.entity.LikeProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LikeDao {

    @Query("SELECT * FROM like")
    fun getAll() : Flow<List<LikeProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: LikeProductEntity)

    @Query("DELETE FROM like WHERE productId=:id")
    suspend fun delete(id: String)

    @Query("DELETE FROM basket")
    suspend fun deleteAll()
}