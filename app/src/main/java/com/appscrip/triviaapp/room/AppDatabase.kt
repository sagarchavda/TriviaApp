package com.appscrip.triviaapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.appscrip.triviaapp.models.History

/**
 * Created by Sagar Chavda on 26/04/20.
 *
 * It's class with contain all room tables dao and instance with build database
 */
@Database(entities = [History::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "TriviaDb"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}