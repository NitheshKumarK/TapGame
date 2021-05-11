package com.nithesh.tapgame.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_table")
data class GameScore(
    @PrimaryKey(autoGenerate = true)
    var gameScoreId: Long = 0L,
    @ColumnInfo(name = "user_name")
    var userName: String = "",
    @ColumnInfo(name = "score")
    var score: Int = 0
)
