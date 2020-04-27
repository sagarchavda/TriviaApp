package com.appscrip.triviaapp.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.appscrip.triviaapp.models.History
import com.appscrip.triviaapp.room.AppDatabase
import com.appscrip.triviaapp.room.HistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Sagar Chavda on 27/04/20.
 *
 * It's get list of history data from room db repository
 */
class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HistoryRepository

    val histories: LiveData<List<History>>

    init {
        val historyDao = AppDatabase.getDatabase(application).historyDao()
        repository = HistoryRepository(historyDao)
        histories = repository.allHistories
    }
}