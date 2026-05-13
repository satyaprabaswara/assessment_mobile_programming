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

    @Query("SELECT * FROM buah WHERE isDeleted = 0 ORDER BY nama ASC")
    fun getBuah(): Flow<List<Buah>>

    @Query("SELECT * FROM buah WHERE isDeleted = 1 ORDER BY nama ASC")
    fun getDeletedBuah(): Flow<List<Buah>>

    @Query("SELECT * FROM buah WHERE id = :id")
    suspend fun getBuahById(id: Long): Buah?

    @Query("UPDATE buah SET isDeleted = 1 WHERE id = :id")
    suspend fun softDelete(id: Long)

    @Query("UPDATE buah SET isDeleted = 0 WHERE id = :id")
    suspend fun restore(id: Long)

    @Query("DELETE FROM buah WHERE id = :id")
    suspend fun deletePermanent(id: Long)
}