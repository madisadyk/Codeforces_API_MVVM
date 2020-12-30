package com.madisadyk.codeforcesapimvvm.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.madisadyk.codeforcesapimvvm.models.CFHandler

@Dao
interface HandlerDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(handler: CFHandler): Long

    @Query("SELECT * FROM handlers")
    fun getAllHandlers():LiveData<List<CFHandler>>

    @Delete
    suspend fun deleteHandler(handler: CFHandler)
}