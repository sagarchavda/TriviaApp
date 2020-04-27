package com.appscrip.triviaapp.ui.summary

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
 * It's create for SummaryActivity and allow to insert data and get count from room db
 */
class SummaryViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: HistoryRepository

    init {
        val historyDao = AppDatabase.getDatabase(application).historyDao()
        repository = HistoryRepository(historyDao)
    }

    /**
     * It's store passed history object into room db
     */
    fun insert(history: History) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(history)
    }

    /**
     * It's return total history count
     */
    fun getCount(): LiveData<Int> {
        return repository.getCount()
    }
}