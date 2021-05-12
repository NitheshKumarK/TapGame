package com.nithesh.tapgame.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GameScore::class], version = 1, exportSchema = false)
abstract class GameScoreDatabase : RoomDatabase() {

    abstract fun getGameScoreDao(): GameScoreDao

    companion object {
        @Volatile
        private var INSTANCE: GameScoreDatabase? = null
        fun getDatabase(application: Application): GameScoreDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    application,
                    GameScoreDatabase::class.java,
                    "game_score_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}