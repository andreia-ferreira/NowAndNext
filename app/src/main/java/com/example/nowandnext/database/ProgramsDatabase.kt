package com.example.nowandnext.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nowandnext.model.DisplayProgram


@Database(entities = [DisplayProgram::class],
    version = 1,
    exportSchema = false)
abstract class ProgramsDatabase : RoomDatabase() {

    abstract fun programsDao(): ProgramsDao

    companion object {
        @Volatile
        private var INSTANCE: ProgramsDatabase? = null

        fun getDatabase(context: Context): ProgramsDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ProgramsDatabase::class.java,
                    "nowAndNextDatabase"
                ).build()
            }
        }
    }
}