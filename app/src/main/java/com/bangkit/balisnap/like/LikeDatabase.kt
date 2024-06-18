package com.bangkit.balisnap.like

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LikeDestination::class], version = 1, exportSchema = false)
abstract class LikeDatabase : RoomDatabase() {

    abstract fun likeDao(): LikeDao

    companion object {
        @Volatile
        private var instance: LikeDatabase? = null
        fun getInstance(context: Context): LikeDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    LikeDatabase::class.java, "culinary_database.db"
                ).build().also { instance = it }
            }
    }
}