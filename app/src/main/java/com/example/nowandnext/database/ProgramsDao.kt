package com.example.nowandnext.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nowandnext.model.DisplayProgram

@Dao
interface ProgramsDao {

    @Query("SELECT * FROM program_table ORDER BY channelPosition")
    suspend fun getDisplayPrograms(): List<DisplayProgram>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDisplayPrograms(list : List<DisplayProgram>)

    @Query("DELETE FROM program_table")
    suspend fun deleteAllPrograms()
}