package com.appscrip.triviaapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appscrip.triviaapp.models.History

/**
 * Created by Sagar Chavda on 26/04/20.
 *
 * It's used to define various actions that want to perform with db like insert, get all, get count
 */
@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(history: History)

    @Query("SELECT * FROM HISTORY")
    fun getHistories() : LiveData<List<History>>

    @Query("SELECT COUNT(id) FROM HISTORY")
    fun getCount(): LiveData<Int>
}