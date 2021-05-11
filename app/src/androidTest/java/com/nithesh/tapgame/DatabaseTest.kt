package com.nithesh.tapgame

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nithesh.tapgame.database.GameScore
import com.nithesh.tapgame.database.GameScoreDao
import com.nithesh.tapgame.database.GameScoreDatabase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var gameScoreDatabase: GameScoreDatabase
    private lateinit var gameScoreDao: GameScoreDao


    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        gameScoreDatabase = Room.inMemoryDatabaseBuilder(
            context,
            GameScoreDatabase::class.java
        ).build()
        gameScoreDao = gameScoreDatabase.getGameScoreDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        gameScoreDatabase.close()
    }


    @Test
    @Throws(Exception::class)
    fun insertAndGetUser() {
        val gameScore = GameScore(userName = "nithesh")
        gameScoreDao.insertGame(gameScore)
        val firstUser: GameScore? = gameScoreDao.getUser(0)
        Assert.assertEquals(firstUser!!.userName, "nithesh")
    }


}
