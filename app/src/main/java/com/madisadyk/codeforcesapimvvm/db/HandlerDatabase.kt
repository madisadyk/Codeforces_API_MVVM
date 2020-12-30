package com.madisadyk.codeforcesapimvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.madisadyk.codeforcesapimvvm.models.CFHandler

@Database(
    entities =[CFHandler::class],
    version = 1
)
abstract class HandlerDatabase : RoomDatabase() {

    abstract fun getHandlerDao(): HandlerDAO

    companion object {
        @Volatile
        private var instance: HandlerDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                HandlerDatabase::class.java,
                "handler_db.db"
            ).build()
    }
}