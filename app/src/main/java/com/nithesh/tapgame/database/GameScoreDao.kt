package com.nithesh.tapgame.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GameScoreDao {

    @Insert
    fun insertGame(gameScore: GameScore)

    @Update
    fun updateGame(gameScore: GameScore)

    @Query("Select * from game_table where gameScoreId=:id")
    fun getUser(id: Long): GameScore?

    @Query("Select * from game_table")
    fun getAllUsers(): LiveData<List<GameScore>>

    @Query("DELETE FROM game_table")
    fun deleteAllGame()

    @Query("Delete from game_table where user_name=:userName")
    fun deleteUserGame(userName: String)

}