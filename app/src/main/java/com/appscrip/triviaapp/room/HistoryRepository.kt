package com.appscrip.triviaapp.room

import androidx.lifecycle.LiveData
import com.appscrip.triviaapp.models.History

/**
 * Created by Sagar Chavda on 27/04/20.
 *
 * It's help to access DAO and it's defined actions then able to use it in view holder
 */
class HistoryRepository(private val historyDao: HistoryDao) {

    val allHistories: LiveData<List<History>> = historyDao.getHistories()

    suspend fun insert(history: History) {
        historyDao.insertHistory(history)
    }

    fun getCount(): LiveData<Int> {
        return historyDao.getCount()
    }
}