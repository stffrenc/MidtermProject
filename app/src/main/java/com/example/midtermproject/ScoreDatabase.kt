package com.example.midtermproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Score::class], version = 1, exportSchema = false)
abstract class ScoreDatabase : RoomDatabase() {
    abstract val scoreDao : ScoreDao
    companion object{
        @Volatile
        private var INSTANCE: ScoreDatabase? = null
        fun getInstance(context: Context): ScoreDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ScoreDatabase::class.java,
                        "score_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}