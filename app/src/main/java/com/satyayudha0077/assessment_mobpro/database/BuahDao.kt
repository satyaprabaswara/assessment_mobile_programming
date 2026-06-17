package com.satyayudha0077.assessment_mobpro.database

import androidx.room.*
import com.satyayudha0077.assessment_mobpro.model.Buah
import kotlinx.coroutines.flow.Flow

@Dao
interface BuahDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // Ditambahkan REPLACE agar aman saat sinkronisasi data cloud
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
    suspend fun deletePermanent(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(buahList: List<Buah>)

    @Query("DELETE FROM buah")
    suspend fun clearAll()
}