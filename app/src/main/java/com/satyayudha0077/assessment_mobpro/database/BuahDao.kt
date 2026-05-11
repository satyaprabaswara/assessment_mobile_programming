package com.satyayudha0077.assessment_mobpro.database

import androidx.room.*
import com.satyayudha0077.assessment_mobpro.model.Buah
import kotlinx.coroutines.flow.Flow

@Dao
interface BuahDao {

    @Insert
    suspend fun insert(buah: Buah)

    @Update
    suspend fun update(buah: Buah)

    @Delete
    suspend fun delete(buah: Buah)

    @Query("SELECT * FROM buah ORDER BY nama ASC")
    fun getBuah(): Flow<List<Buah>>

    @Query("SELECT * FROM buah WHERE id = :id")
    suspend fun getBuahById(id: Long): Buah?

    @Query("DELETE FROM buah WHERE id = :id")
    suspend fun deleteById(id: Long)
}